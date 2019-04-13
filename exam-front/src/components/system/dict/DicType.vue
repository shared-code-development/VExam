<template>
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过字典名搜索,记得回车哦..."
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
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddDictTypeView">
            添加
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="dictTypeList"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%">
            <el-table-column
              type="selection"
              align="center"
              width="30">
            </el-table-column>
            <el-table-column
              prop="dicTypeId"
              align="center"
              fixed
              label="ID"
              width="200">
            </el-table-column>
            <el-table-column
              prop="dicTypeName"
              width="150"
              align="center"
              label="字典名称">
            </el-table-column>
            <el-table-column
              width="200"
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
              width="200"
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
              align="center"
              label="操作"
              width="195">
              <template slot-scope="scope">
                <el-button @click="showEditDictTypeView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteDicType(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="dictTypeList.length>0" :disabled="multipleSelection.length==0"
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
    <el-form :model="dictType" :rules="rules" ref="addDictTypeForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="50%">
          <el-row :span="6">
            <el-col>
                <el-form-item label="字典类型:" prop="dicTypeName"></el-form-item>
                <el-input prefix-icon="el-icon-edit" v-model="dictType.dicTypeName" size="mini" style="width: 200px"
                        placeholder="请输入员工字典类型"></el-input>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addDictType('addDictTypeForm')">确 定</el-button>
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
        dictTypeList: [],
        keywords: '',
        enrollmentDate: '',
        dialogTitle: '',
        multipleSelection: [],
        totalCount: -1,
        currentPage: 1,
        dialogVisible: false,
        tableLoading: false,
        advanceSearchViewVisible: false,
        dictType: {
          dicTypeId: '',
          dicTypeName: ''
        },
        rules: {
          dicTypeName: [{required: true, message: '必填:字典名称', trigger: 'blur'}]
        }
      };
    },
    mounted: function () {
      this.loadDictTypeList();
    },
    methods: {
      cancelSearch() {
        this.advanceSearchViewVisible = false;
        this.emptyDicTypeData();
        this.loadDictTypeList();
      },
      cancelEidt() {
        this.dialogVisible = false;
        this.emptyDicTypeData();
      },
      showAdvanceSearchView() {
        this.advanceSearchViewVisible = !this.advanceSearchViewVisible;
        this.keywords = '';
        if (!this.advanceSearchViewVisible) {
          this.emptyDicTypeData();
          this.enrollmentDate = '';
          this.loadDictTypeList();
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
      deleteDicType(row) {
        this.$confirm('此操作将永久删除[' + row.dicTypeName + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.dicTypeId);
        }).catch(() => {
        });
      },
      doDelete(ids) {
        this.tableLoading = true;
        let _this = this;
        this.deleteRequest("/dic/type/" + ids).then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.loadDictTypeList();
          }
        })
      },
      keywordsChange(val) {
        if (val == '') {
          this.loadDictTypeList();
        }
      },
      searchDict() {
        this.loadDictTypeList();
      },
      currentChange(currentChange) {
        this.currentPage = currentChange;
        this.loadDictTypeList();
      },
      loadDictTypeList() {
        let _this = this;
        this.tableLoading = true;
        this.getRequest("/dic/type/list?pageNum=" + this.currentPage)
          .then(resp => {
            this.tableLoading = false;
            if (resp && resp.status == 200) {
              let data = resp.data;
              _this.dictTypeList = data.obj.list;
              _this.totalCount = data.obj.total;
            }
          })
      },
      addDictType(formName) {
        let _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.dictType.dicTypeId) {
              //更新
              this.tableLoading = true;
              this.putRequest("/dic/type", this.dictType).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyDicTypeData();
                  _this.loadDictTypeList();
                }
              })
            } else {
              //添加
              this.tableLoading = true;
              this.postRequest("/dic/type", this.dictType).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyDicTypeData();
                  _this.loadDictTypeList();
                }
              })
            }
          } else {
            return false;
          }
        });
      },
      cancelEdit() {
        this.dialogVisible = false;
        this.emptyDicTypeData();
      },
      showEditDictTypeView(row) {
        this.dialogTitle = "编辑字典";
        this.dictType = row;
        this.dialogVisible = true;
      },
      showAddDictTypeView() {
        this.dialogTitle = "添加字典";
        this.dialogVisible = true;
      },
      emptyDicTypeData() {
        this.dictType = {
          dicTypeName: ''
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
