<template>
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过专业名称搜索专业,记得回车哦..."
            clearable
            @change="keyWordsChange"
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchMajor"
            prefix-icon="el-icon-search"
            v-model="keyWords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchMajor">搜索
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
            :data="majorList"
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
              prop="majorId"
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
              prop="academy.academyName"
              label="所属学院"
              width="200">
            </el-table-column>
            <el-table-column
              width="180"
              align="center"
              label="创建时间">
              <template slot-scope="scope">{{ scope.row.createTime | formatDateTimeHhMmSs}}</template>
            </el-table-column>
            <el-table-column
              width="100"
              align="center"
              prop="creator"
              label="创建者">
            </el-table-column>
            <el-table-column
              width="180"
              align="center"
              label="更新时间">
              <template slot-scope="scope">{{ scope.row.updateTime | formatDateTimeHhMmSs}}</template>
            </el-table-column>
            <el-table-column
              width="100"
              align="center"
              prop="creator"
              label="更新者">
            </el-table-column>
            <el-table-column
              align="center"
              width="100"
              label="是否禁用">
              <template slot-scope="scope">{{ scope.row.isDel | generateDisable}}</template>
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="195">
              <template slot-scope="scope">
                <el-button @click="showEditMajorView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteMajor(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="majorList.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyMajor">批量删除
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
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="30%">
          <el-row>
            <el-col :span="6">
              <div>
                <span>所属学院</span>
                <el-select v-model="major.academyId" style="width: 200px" placeholder="请选择" size="mini">
                  <el-option
                    v-for="item in academyList"
                    :key="item.academyId"
                    :label="item.academyName"
                    :value="item.academyId"
                    :disabled="!item.isDel">
                  </el-option>
                </el-select>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="专业名称:" prop="majorName">
                  <el-input prefix-icon="el-icon-edit" v-model="major.majorName" size="mini" style="width: 150px"
                            placeholder="请输入专业名称">
                  </el-input>
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
        majorList: [],
        academyList: [],
        academy: {
          academyId: '',
          academyName: ''
        },
        keyWords: '',
        dialogTitle: '',
        multipleSelection: [],
        depTextColor: '#c0c4cc',
        totalCount: -1,
        currentPage: 1,
        dialogVisible: false,
        tableLoading: false,
        advanceSearchViewVisible: false,
        major: {
          majorId: '',
          majorName: '',
          academyId: ''
        },
        rules: {
          majorId: [{required: true, message: '必填:专业编号', trigger: 'blur'}],
          majorName: [{required: true, message: '必填:专业名称', trigger: 'blur'}]
        }
      };
    },
    mounted: function () {
      this.loadMajoies();
      this.initData();
    },
    methods: {
      initData(){
        let _this = this;
        this.getRequest("/academy/list?pageNum=1&pageSize=1000")
          .then(resp => {
            if (resp && resp.status == 200) {
              let data = resp.data;
              _this.academyList = data.obj.list;
            }
          });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteMajor(row) {
        this.$confirm('此操作将永久删除[' + row.majorName + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.majorId);
        }).catch(() => {
        });
      },
      doDelete(id) {
        this.tableLoading = true;
        let _this = this;
        this.deleteRequest("/major/" + id).then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.loadMajoies();
          }
        })
      },
      keyWordsChange(val) {
        if (val == '') {
          this.loadMajoies();
        }
      },
      searchMajor() {
        this.loadMajoies();
      },
      currentChange(currentChange) {
        this.currentPage = currentChange;
        this.loadMajoies();
      },
      loadMajoies() {
        let _this = this;
        this.tableLoading = true;
        this.getRequest("/major/list?pageNum=" + this.currentPage + "&pageSize=10&keyWords="+this.keyWords)
          .then(resp => {
            this.tableLoading = false;
            if (resp && resp.status == 200) {
              let data = resp.data;
              _this.majorList = data.obj.list;
              _this.totalCount = data.obj.total;
            }
          })
      },
      addMajor(formName) {
        let _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.major.majorId) {
              //更新
              this.tableLoading = true;
              this.putRequest("/major", this.major).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyMajorData();
                  _this.loadMajoies();
                }
              })
            } else {
              //添加
              this.tableLoading = true;
              this.postRequest("/major", this.major).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyMajorData();
                  _this.loadMajoies();
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
      deleteManyMajor() {
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let idArray = [];
          for (let i = 0; i < this.multipleSelection.length; i++) {
            idArray.push(this.multipleSelection[i].majorId);
          }
          this.tableLoading = true;
          let _this = this;
          this.deleteManyRequest("/major", {"ids": idArray}).then(resp => {
            _this.tableLoading = false;
            if (resp && resp.status == 200) {
              _this.loadMajoies();
            }
          });
        }).catch(() => {
        });
      },
      handleNodeClick(data) {
        this.depTextColor = '#606266';
      },
      handleNodeClick2(data) {
        this.depTextColor = '#606266';
      },
      showEditMajorView(row) {
        this.dialogTitle = "编辑专业";
        this.major = row;
        this.dialogVisible = true;
      },
      showAddMajorView() {
        this.dialogTitle = "添加专业";
        this.dialogVisible = true;
      },
      emptyMajorData() {
        this.major = {
          majorId: '',
          majorName: ''
        }
      }
    }
  };
</script>
