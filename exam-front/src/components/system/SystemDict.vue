<template>
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过用户名搜索用户,记得回车哦..."
            clearable
            @change="keywordsChange"
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchUser"
            prefix-icon="el-icon-search"
            v-model="keywords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchUser">搜索
          </el-button>
          <el-button slot="reference" type="primary" size="mini" style="margin-left: 5px"
                     @click="showAdvanceSearchView"><i
            class="fa fa-lg" v-bind:class="[advanceSearchViewVisible ? faangledoubleup:faangledoubledown]"
            style="margin-right: 5px"></i>高级搜索
          </el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddUserView">
            添加
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <transition name="slide-fade">
            <div
              style="margin-bottom: 10px;border: 1px;border-radius: 5px;border-style: solid;padding: 5px 0px 5px 0px;box-sizing:border-box;border-color: #20a0ff"
              v-show="advanceSearchViewVisible">
              <el-row>
                <el-col :span="10">
                  入学日期:
                  <el-date-picker
                    v-model="enrollmentDate"
                    unlink-panels
                    size="mini"
                    type="daterange"
                    value-format="yyyy-MM-dd"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                  </el-date-picker>
                </el-col>
                <el-col :span="5" :offset="4">
                  <el-button size="mini" @click="cancelSearch">取消</el-button>
                  <el-button icon="el-icon-search" type="primary" size="mini" @click="searchUser">搜索</el-button>
                </el-col>
              </el-row>
            </div>
          </transition>
          <el-table
            :data="users"
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
              prop="dicId"
              align="left"
              fixed
              label="ID"
              width="90">
            </el-table-column>
            <el-table-column
              prop="dicKey"
              width="85"
              align="left"
              label="key">
            </el-table-column>
            <el-table-column
              prop="dicValue"
              label="value"
              width="50">
            </el-table-column>
            <el-table-column
              prop="dicType"
              width="150"
              align="left"
              label="类型">
            </el-table-column>
            <el-table-column
              prop="isDel"
              width="70"
              label="是否禁用">
            </el-table-column>
            <el-table-column
              width="85"
              align="left"
              label="创建时间">
              <template slot-scope="scope">{{ scope.row.createTime | formatDate}}</template>
            </el-table-column>
            <el-table-column
              width="50"
              prop="creator"
              label="创建者">
            </el-table-column>
            <el-table-column
              width="85"
              align="left"
              label="更新时间">
              <template slot-scope="scope">{{ scope.row.updateTime | formatDate}}</template>
            </el-table-column>
            <el-table-column
              width="50"
              prop="creator"
              label="更新者">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="195">
              <template slot-scope="scope">
                <el-button @click="showEditUserView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteUser(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="users.length>0" :disabled="multipleSelection.length==0"
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
  </div>
</template>
<script>
  export default {
    data() {
      return {
        users: [],
        keywords: '',
        enrollmentDate: '',
        dialogTitle: '',
        multipleSelection: [],
        depTextColor: '#c0c4cc',
        totalCount: -1,
        currentPage: 1,
        defaultProps: {
          label: 'name',
          isLeaf: 'leaf',
          children: 'children'
        },
        dialogVisible: false,
        tableLoading: false,
        advanceSearchViewVisible: false,
        showOrHidePop: false,
        showOrHidePop2: false,
        user: {
          name: '',
          sex: '',
          birthday: '',
          idCard: '',
          nationId: '',
          nativePlace: '',
          email: '',
          phone: '',
          address: ''
        },
        rules: {
          name: [{required: true, message: '必填:姓名', trigger: 'blur'}],
          sex: [{required: true, message: '必填:性别', trigger: 'blur'}],
          birthday: [{required: true, message: '必填:出生日期', trigger: 'blur'}],
          idCard: [{
            required: true,
            message: '必填:身份证号码',
            trigger: 'blur'
          }, {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: '身份证号码格式不正确',
            trigger: 'blur'
          }],
          nationId: [{required: true, message: '必填:民族', trigger: 'change'}],
          nativePlace: [{required: true, message: '必填:籍贯', trigger: 'blur'}],
          email: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
          phone: [{required: true, message: '必填:电话号码', trigger: 'blur'}],
          address: [{required: true, message: '必填:联系地址', trigger: 'blur'}],
        }
      };
    },
    mounted: function () {
      this.initData();
      this.loadUsers();
    },
    methods: {
      fileUploadSuccess(response, file, fileList) {
        if (response) {
          this.$message({type: response.status, message: response.msg});
        }
        this.loadUsers();
        this.fileUploadBtnText = '导入数据';
      },
      fileUploadError(err, file, fileList) {
        this.$message({type: 'error', message: "导入失败!"});
        this.fileUploadBtnText = '导入数据';
      },
      beforeFileUpload(file) {
        this.fileUploadBtnText = '正在导入';
      },
      exportUsers() {
        window.open("/user/export", "_parent");
      },
      cancelSearch() {
        this.advanceSearchViewVisible = false;
        this.emptyUserData();
        this.enrollmentDate = '';
        this.loadUsers();
      },
      showAdvanceSearchView() {
        this.advanceSearchViewVisible = !this.advanceSearchViewVisible;
        this.keywords = '';
        if (!this.advanceSearchViewVisible) {
          this.emptyUserData();
          this.enrollmentDate = '';
          this.loadUsers();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteManyUsers() {
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
      deleteUser(row) {
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
        this.deleteRequest("/user/" + ids).then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.loadUsers();
          }
        })
      },
      keywordsChange(val) {
        if (val == '') {
          this.loadUsers();
        }
      },
      searchUser() {
        this.loadUsers();
      },
      currentChange(currentChange) {
        this.currentPage = currentChange;
        this.loadUsers();
      },
      loadUsers() {
        let _this = this;
        this.tableLoading = true;
        this.getRequest("/user/list?pageNum=" + this.currentPage +
          "&pageSize=10&keywords=" + this.keywords +
          "&enrollmentDate=" + this.enrollmentDate)
          .then(resp => {
            debugger
            this.tableLoading = false;
            if (resp && resp.status == 200) {
              let data = resp.data;
              _this.users = data.obj.list;
              _this.totalCount = data.obj.total;
              //            _this.emptyUserData();
            }
          })
      },
      addUser(formName) {
        let _this = this;
        this.$refs[formName].validate((valid) => {
          debugger
          if (valid) {
            if (this.user.id) {
              //更新
              this.tableLoading = true;
              this.putRequest("/user", this.user).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyUserData();
                  _this.loadUsers();
                }
              })
            } else {
              //添加
              this.tableLoading = true;
              this.postRequest("/user", this.user).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyUserData();
                  _this.loadUsers();
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
        this.emptyUserData();
      },
      showDepTree() {
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
      initData() {
        let _this = this;
        this.getRequest("/user/nation/list").then(resp => {
          if (resp && resp.status == 200) {
            _this.nations = resp.data.obj;
          }
        })
      },
      showEditUserView(row) {
        console.log(row)
        this.dialogTitle = "编辑员工";
        this.user = row;
        this.user.birthday = this.formatDate(row.birthday);
        this.user.nationId = row.nation.id;
        this.dialogVisible = true;
      },
      showAddUserView() {
        this.dialogTitle = "添加员工";
        this.dialogVisible = true;
        let _this = this;
        this.getRequest("/user/nextUserId").then(resp => {
          if (resp && resp.status == 200) {
            _this.user.workID = resp.data.obj;
          }
        })
      },
      emptyUserData() {
        this.user = {
          name: '',
          sex: '',
          email: '',
          phone: '',
          address: '',
          birthday: ''
        }
      }
    }
  };
</script>
<style>
  .el-dialog__body {
    padding-top: 0px;
    padding-bottom: 0px;
  }

  .slide-fade-enter-active {
    transition: all .8s ease;
  }

  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .slide-fade-enter, .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }
</style>
