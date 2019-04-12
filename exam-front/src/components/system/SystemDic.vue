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
            @keyup.enter.native="searchDict"
            prefix-icon="el-icon-search"
            v-model="keywords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchDict">搜索
          </el-button>
          <el-button slot="reference" type="primary" size="mini" style="margin-left: 5px"
                     @click="showAdvanceSearchView"><i
            class="fa fa-lg" v-bind:class="[advanceSearchViewVisible ? faangledoubleup:faangledoubledown]"
            style="margin-right: 5px"></i>高级搜索
          </el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddDictView">
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
                  <el-button icon="el-icon-search" type="primary" size="mini" @click="searchDict">搜索</el-button>
                </el-col>
              </el-row>
            </div>
          </transition>
          <el-table
            :data="dictList"
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
                <el-button @click="showEditDictView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteDict(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="dicts.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyDicts">批量删除
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
        dictList: [],
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
        dict: {
          name: '',
          sex: '',
          birthday: '',
          idCard: '',
          nationId: '',
          nativePlace: '',
          email: '',
          phone: '',
          address: ''
        }
      };
    },
    mounted: function () {
      this.loadDicts();
    },
    methods: {
      cancelSearch() {
        this.advanceSearchViewVisible = false;
        this.emptyDictData();
        this.enrollmentDate = '';
        this.loadDicts();
      },
      showAdvanceSearchView() {
        this.advanceSearchViewVisible = !this.advanceSearchViewVisible;
        this.keywords = '';
        if (!this.advanceSearchViewVisible) {
          this.emptyDictData();
          this.enrollmentDate = '';
          this.loadDicts();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteManyDicts() {
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
      deleteDict(row) {
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
        this.deleteRequest("/dict/" + ids).then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.loadDicts();
          }
        })
      },
      keywordsChange(val) {
        if (val == '') {
          this.loadDicts();
        }
      },
      searchDict() {
        this.loadDicts();
      },
      currentChange(currentChange) {
        this.currentPage = currentChange;
        this.loadDicts();
      },
      loadDicts() {
        let _this = this;
        this.tableLoading = true;
        this.getRequest("/dict/list?pageNum=" + this.currentPage + "&pageSize=10")
          .then(resp => {
            debugger
            this.tableLoading = false;
            if (resp && resp.status == 200) {
              let data = resp.data;
              _this.dictList = data.obj.list;
              _this.totalCount = data.obj.total;
              //            _this.emptyDictData();
            }
          })
      },
      addDict(formName) {
        let _this = this;
        this.$refs[formName].validate((valid) => {
          debugger
          if (valid) {
            if (this.dict.id) {
              //更新
              this.tableLoading = true;
              this.putRequest("/dict", this.dict).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyDictData();
                  _this.loadDicts();
                }
              })
            } else {
              //添加
              this.tableLoading = true;
              this.postRequest("/dict", this.dict).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyDictData();
                  _this.loadDicts();
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
        this.emptyDictData();
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
      showEditDictView(row) {
        this.dialogTitle = "编辑员工";
        this.dict = row;
        this.dict.birthday = this.formatDate(row.birthday);
        this.dict.nationId = row.nation.id;
        this.dialogVisible = true;
      },
      showAddDictView() {
        this.dialogTitle = "添加员工";
        this.dialogVisible = true;
        let _this = this;
        this.getRequest("/dict/nextDictId").then(resp => {
          if (resp && resp.status == 200) {
            _this.dict.workID = resp.data.obj;
          }
        })
      },
      emptyDictData() {
        this.dict = {
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
