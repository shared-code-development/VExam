<template>
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过题目名称搜索题目,记得回车哦..."
            clearable
            @change="keyWordsChange"
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchChoice"
            prefix-icon="el-icon-search"
            v-model="keyWords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchChoice">搜索
          </el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddChoiceView">
            添加
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="choiceList"
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
              prop="choiceId"
              width="200"
              align="left"
              label="题目编号">
            </el-table-column>
            <el-table-column
              prop="choiceName"
              label="题目"
              width="200">
            </el-table-column>
            <el-table-column
              prop="academy.academyName"
              label="类型"
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
                <el-button @click="showEditChoiceView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button @click="showEditChoiceView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">详情
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteChoice(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="choiceList.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyChoice">批量删除
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
    <el-form :model="choice" :rules="rules" ref="addChoiceForm" style="margin: 0px;padding: 0px;">
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
                <el-select v-model="choice.academyId" style="width: 200px" placeholder="请选择" size="mini">
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
                <el-form-item label="题目名称:" prop="choiceName">
                  <el-input prefix-icon="el-icon-edit" v-model="choice.choiceName" size="mini" style="width: 150px"
                            placeholder="请输入题目名称">
                  </el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addChoice('addChoiceForm')">确 定</el-button>
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
        choiceList: [],
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
        choice: {
          choiceId: '',
          choiceName: '',
          academyId: ''
        },
        rules: {
          choiceId: [{required: true, message: '必填:题目编号', trigger: 'blur'}],
          choiceName: [{required: true, message: '必填:题目名称', trigger: 'blur'}]
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
      deleteChoice(row) {
        this.$confirm('此操作将永久删除[' + row.choiceName + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.choiceId);
        }).catch(() => {
        });
      },
      doDelete(id) {
        this.tableLoading = true;
        let _this = this;
        this.deleteRequest("/choice/" + id).then(resp => {
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
      searchChoice() {
        this.loadMajoies();
      },
      currentChange(currentChange) {
        this.currentPage = currentChange;
        this.loadMajoies();
      },
      loadMajoies() {
        let _this = this;
        this.tableLoading = true;
        this.getRequest("/choice/list?pageNum=" + this.currentPage + "&pageSize=10&keyWords="+this.keyWords)
          .then(resp => {
            this.tableLoading = false;
            if (resp && resp.status == 200) {
              let data = resp.data;
              _this.choiceList = data.obj.list;
              _this.totalCount = data.obj.total;
            }
          })
      },
      addChoice(formName) {
        let _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.choice.choiceId) {
              //更新
              this.tableLoading = true;
              this.putRequest("/choice", this.choice).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyChoiceData();
                  _this.loadMajoies();
                }
              })
            } else {
              //添加
              this.tableLoading = true;
              this.postRequest("/choice", this.choice).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyChoiceData();
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
        this.emptyChoiceData();
      },
      deleteManyChoice() {
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let idArray = [];
          for (let i = 0; i < this.multipleSelection.length; i++) {
            idArray.push(this.multipleSelection[i].choiceId);
          }
          this.tableLoading = true;
          let _this = this;
          this.deleteManyRequest("/choice", {"ids": idArray}).then(resp => {
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
      showEditChoiceView(row) {
        this.dialogTitle = "编辑题目";
        this.choice = row;
        this.dialogVisible = true;
      },
      showAddChoiceView() {
        this.dialogTitle = "添加题目";
        this.dialogVisible = true;
      },
      emptyChoiceData() {
        this.choice = {
          choiceId: '',
          choiceName: ''
        }
      }
    }
  };
</script>
