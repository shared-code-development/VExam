<template>
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过专业名称搜索专业,记得回车哦..."
            clearable
            @change="keywordsChange"
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchMajor"
            prefix-icon="el-icon-search"
            v-model="keywords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchMajor">搜索
          </el-button>
          <el-button slot="reference" type="primary" size="mini" style="margin-left: 5px"
                     @click="showAdvanceSearchView"><i
            class="fa fa-lg" v-bind:class="[advanceSearchViewVisible ? faangledoubleup:faangledoubledown]"
            style="margin-right: 5px"></i>高级搜索
          </el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddMajorView">
            添加
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="majors"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%">
            <el-table-column
              type="selection"
              align="left"
              width="30">
            </el-table-column>
            <el-table-column
              prop="id"
              align="left"
              fixed
              label="ID"
              width="90">
            </el-table-column>
            <el-table-column
              prop="majorNo"
              width="200"
              align="left"
              label="专业编号">
            </el-table-column>
            <el-table-column
              prop="majorName"
              label="专业名称"
              width="200">
            </el-table-column>
            <el-table-column
              prop="majorName"
              label="所属院系"
              width="200">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="195">
              <template slot-scope="scope">
                <el-button @click="showEditUserView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button style="padding: 3px 4px 3px 4px;margin: 2px" type="primary"
                           size="mini">查看高级资料
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteUser(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="majors.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyUsers">批量删除
            </el-button>
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="totalCount">
            </el-pagination>
          </div>
        </div>
      </el-main>
    </el-container>
    <el-form :model="major" :rules="rules" ref="addMajorForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"¡
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="77%">
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="专业编号:" prop="academyNo">
                  <el-input prefix-icon="el-icon-edit" v-model="major.majorNo" size="mini" style="width: 150px"
                            placeholder="请输入专业编号"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="专业名称:" prop="academyName">
                  <el-input prefix-icon="el-icon-edit" v-model="major.majorName" size="mini" style="width: 150px"
                            placeholder="请输入专业名称"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addMajor('addMajorForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        majors: [],
        keywords: '',
        enrollmentDate: '',
        faangledoubleup: 'fa-angle-double-up',
        faangledoubledown: 'fa-angle-double-down',
        dialogTitle: '',
        multipleSelection: [],
        depTextColor: '#c0c4cc',
        totalCount: -1,
        currentPage: 1,
        /*defaultProps: {
          label: 'academyName',
          isLeaf: 'leaf',
          children: 'children'
        },*/
        dialogVisible: false,
        tableLoading: false,
        advanceSearchViewVisible: false,
        showOrHidePop: false,
        showOrHidePop2: false,
        major: {
          id: '',
          majorNo: '',
          majorName: '',
          academyId: ''
        },
        rules: {
          majorNo: [{required: true, message: '必填:专业编号', trigger: 'blur'}],
          majorName: [{required: true, message: '必填:专业名称', trigger: 'blur'}]
        }
      };
    },
    mounted: function () {
      this.initData();
      this.loadMajors();
    },
    methods: {
      cancelSearch() {
        this.advanceSearchViewVisible = false;
        this.emptyMajorData();
        this.enrollmentDate = '';
        this.loadMajors();
      },
      showAdvanceSearchView() {
        this.advanceSearchViewVisible = !this.advanceSearchViewVisible;
        this.keywords = '';
        if (!this.advanceSearchViewVisible) {
          this.emptyMajorData();
          this.loadMajors();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteManyMajors() {
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let ids = '';
          for (let i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDelete(ids);
        }).catch(() => {
        });
      },
      deleteMajor(row) {
        this.$confirm('此操作将永久删除[' + row.name + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.id);
        }).catch(() => {
        });
      },
      doDelete(ids) {
        this.tableLoading = true;
        let _this = this;
        this.deleteRequest("/major/" + ids).then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.loadMajors();
          }
        })
      },
      keywordsChange(val) {
        if (val == '') {
          this.loadUsers();
        }
      },
      searchMajor() {
        this.loadMajors();
      },
      currentChange(currentChange) {
        this.currentPage = currentChange;
        this.loadMajors();
      },
      loadMajors() {
        let _this = this;
        this.tableLoading = true;
        this.getRequest("/major/list?pageNum=" + this.currentPage + "&pageSize=10")
          .then(resp => {
            this.tableLoading = false;
            if (resp && resp.status == 200) {
              let data = resp.data;
              _this.major = data.obj.list;
              _this.totalCount = data.obj.total;
            }
          })
      },
      addMajor(formName) {
        let _this = this;
        this.$refs[formName].validate((valid) => {
          debugger
          if (valid) {
            if (this.major.id) {
              //更新
              this.tableLoading = true;
              this.putRequest("/major", this.major).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyMajorData();
                  _this.loadMajors();
                }
              })
            } else {
              //添加
              this.tableLoading = true;
              this.postRequest("/major", this.user).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyMajorData();
                  _this.loadMajors();
                }
              })
            }
          } else {
            return false;
          }
        });
      },
      cancelEidt() {
        this.dialogVisible = false;
        this.emptyMajorData();
      },
      showMajorTree() {
        this.showOrHidePop = !this.showOrHidePop;
      },
      showDepTree2() {
        this.showOrHidePop2 = !this.showOrHidePop2;
      },
      handleNodeClick(data) {
        this.showOrHidePop = false;
        this.depTextColor = '#606266';
      },
      handleNodeClick2(data) {
        this.showOrHidePop2 = false;
        this.depTextColor = '#606266';
      },
      showEditMajorView(row) {
        console.log(row)
        this.dialogTitle = "编辑专业";
        this.user = row;
        this.dialogVisible = true;
      },
      showAddMajorView() {
        this.dialogTitle = "添加专业";
        this.dialogVisible = true;
        let _this = this;
        this.getRequest("/user/nextMajorId").then(resp => {
          if (resp && resp.status == 200) {
            _this.major.id = resp.data.obj;
          }
        })
      },
      emptyMajorData() {
        this.Major = {
          id: '',
          majorName: '',
          majorNo: ''
        }
      }
    }
  };
</script>
