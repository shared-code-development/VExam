<template>
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过试卷名称搜索试卷,记得回车哦..."
            clearable
            @change="keyWordsChange"
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchExamPaper"
            prefix-icon="el-icon-search"
            v-model="keyWords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchExamPaper">
            搜索
          </el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddExamPaperView">
            添加
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="examPaperList"
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
              prop="paperId"
              width="200"
              align="left"
              label="试卷编号">
            </el-table-column>
            <el-table-column
              prop="paperName"
              label="试卷名称"
              width="200">
            </el-table-column>
            <el-table-column
              prop="course.courseName"
              label="科目"
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
              align="center"
              label="操作"
              width="280">
              <template slot-scope="scope">
                <el-button @click="showEditExamPaperView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button @click="showEditExamPaperView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">详情
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteExamPaper(scope.row)">删除
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="showGroupExamPaper(scope.row)">组卷
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="showAutoGroupExamPaper(scope.row)">自动组卷
                </el-button>
                <el-button type="success" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="showExamPaper(scope.row)">查看试卷
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="examPaperList.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyExamPaper">批量删除
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
    <el-form :model="handlingExamPaper" :rules="rules" ref="addExamPaperForm" style="margin: 0px;padding: 0px;">
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
                <span>所属科目</span>
                <el-select v-model="handlingExamPaper.courseId" style="width: 200px" placeholder="请选择" size="mini">
                  <el-option
                    v-for="item in courseList"
                    :key="item.courseId"
                    :label="item.courseName"
                    :value="item.courseId"
                    :disabled="!item.isDel">
                  </el-option>
                </el-select>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <div>
                <el-form-item label="试卷名称:" prop="paperName">
                  <el-input prefix-icon="el-icon-edit" v-model="handlingExamPaper.paperName" size="mini" style="width: 150px"
                            placeholder="请输入试卷名称">
                  </el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addExamPaper('addExamPaperForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>
    <el-form :model="groupExamPaperForm" ref="groupExamPaperForm" style="margin: 0px;padding: 0px;">
        <el-dialog
          :title="groupExamPaperDialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="groupExamPaperDialogVisible"
          width="80%" :fullscreen="true">
            <el-tabs :tab-position="tabPosition" style="height: 100%;">
              <el-tab-pane label="选择题">
                <div style="text-align: center">
                  <el-transfer
                    style="text-align: left; display: inline-block"
                    v-model="checkedChoiceList"
                    filterable
                    :titles="['题库', '已出题目']"
                    :button-texts="['撤题', '出题']"
                    :format="{
                      noChecked: '${total}',
                      hasChecked: '${checked}/${total}'
                    }"
                    @change="groupExamPaper"
                    :data="choiceList"
                  >
                    <el-button class="transfer-footer" slot="right-footer" size="small" @click="addChoiceToExamPaper">添加到试卷</el-button>
                  </el-transfer>
                </div>
              </el-tab-pane>
              <el-tab-pane label="判断题">
                <div style="text-align: center">
                  <el-transfer
                    style="text-align: left; display: inline-block"
                    v-model="checkedJudgeList"
                    filterable
                    :titles="['题库', '已出题目']"
                    :button-texts="['撤题', '出题']"
                    :format="{
                      noChecked: '${total}',
                      hasChecked: '${checked}/${total}'
                    }"
                    @change="groupExamPaper"
                    :data="judgeList"
                  >
                    <el-button class="transfer-footer" slot="right-footer" size="small" @click="addJudgeToExamPaper">添加到试卷</el-button>
                  </el-transfer>
                </div>
              </el-tab-pane>
              <el-tab-pane label="简答题">
                <div style="text-align: center">
                  <el-transfer
                    style="text-align: left; display: inline-block"
                    v-model="checkSampleAnswerList"
                    filterable
                    :titles="['题库', '已出题目']"
                    :button-texts="['撤题', '出题']"
                    :format="{
                      noChecked: '${total}',
                      hasChecked: '${checked}/${total}'
                    }"
                    @change="groupExamPaper"
                    :data="sampleAnswerList"
                  >
                    <el-button class="transfer-footer" slot="right-footer" size="small" @click="addSampleAnswerToExamPaper">添加到试卷</el-button>
                  </el-transfer>
                </div>
              </el-tab-pane>
            </el-tabs>
        </el-dialog>
    </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        tabPosition: 'right',
        groupExamPaperForm: {},
        groupExamPaperDialogTitle: '',
        groupExamPaperDialogVisible: false,
        examPaperList: [],
        courseList: [],
        choiceList: [],
        judgeList: [],
        checkedChoiceList: [],
        checkedJudgeList: [],
        sampleAnswerList: [],
        checkSampleAnswerList: [],
        course: {
          courseId: '',
          courseName: ''
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
        handlingExamPaper: {
          paperId: '',
          paperName: '',
          courseId: ''
        },
        rules: {
          paperId: [{required: true, message: '必填:试卷编号', trigger: 'blur'}],
          paperName: [{required: true, message: '必填:试卷名称', trigger: 'blur'}]
        }
      };
    },
    mounted: function () {
      this.loadPaperList();
      this.initData();
    },
    methods: {
      initData() {
        let _this = this;
        this.getRequest("/course/list?pageNum=1&pageSize=1000")
          .then(resp => {
            if (resp && resp.status == 200) {
              let data = resp.data;
              _this.courseList = data.obj.list;
            }
          });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteExamPaper(row) {
        this.$confirm('此操作将永久删除[' + row.paperName + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.paperId);
        }).catch(() => {
        });
      },
      doDelete(id) {
        this.tableLoading = true;
        let _this = this;
        this.deleteRequest("/examPaper/" + id).then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.loadPaperList();
          }
        })
      },
      keyWordsChange(val) {
        if (val == '') {
          this.loadPaperList();
        }
      },
      searchExamPaper() {
        this.loadPaperList();
      },
      currentChange(currentChange) {
        this.currentPage = currentChange;
        this.loadPaperList();
      },
      loadPaperList() {
        let _this = this;
        this.tableLoading = true;
        this.getRequest("/examPaper/list?pageNum=" + this.currentPage + "&pageSize=10&keyWords=" + this.keyWords)
          .then(resp => {
            this.tableLoading = false;
            if (resp && resp.status == 200) {
              let data = resp.data;
              _this.examPaperList = data.obj.list;
              _this.totalCount = data.obj.total;
            }
          })
      },
      addExamPaper(formName) {
        let _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.examPaper.paperId) {
              //更新
              this.tableLoading = true;
              this.putRequest("/examPaper", this.examPaper).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyExamPaperData();
                  _this.loadPaperList();
                }
              })
            } else {
              //添加
              this.tableLoading = true;
              this.postRequest("/examPaper", this.examPaper).then(resp => {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  _this.dialogVisible = false;
                  _this.emptyExamPaperData();
                  _this.loadPaperList();
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
        this.emptyExamPaperData();
      },
      deleteManyExamPaper() {
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let idArray = [];
          for (let i = 0; i < this.multipleSelection.length; i++) {
            idArray.push(this.multipleSelection[i].paperId);
          }
          this.tableLoading = true;
          let _this = this;
          this.deleteManyRequest("/examPaper", {"ids": idArray}).then(resp => {
            _this.tableLoading = false;
            if (resp && resp.status == 200) {
              _this.loadPaperList();
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
      showEditExamPaperView(row) {
        this.dialogTitle = "编辑试卷";
        this.examPaper = row;
        this.dialogVisible = true;
      },
      showAddExamPaperView() {
        this.dialogTitle = "添加试卷";
        this.dialogVisible = true;
      },
      showGroupExamPaper(row) {
        this.handlingExamPaper = row;
        this.groupExamPaperDialogTitle = "组卷中...";
        this.groupExamPaperDialogVisible = true;
        this.choiceList = [];
        this.judgeList = [];
        this.sampleAnswerList = [];
        this.getRequest("/choice/list?pageNum=1&pageSize=1000&courseId=" + row.courseId)
          .then(resp => {
            if (resp && resp.status == 200) {
              let data = resp.data.obj.list;
              for (let i = 0; i < data.length; i++) {
                this.choiceList.push({
                  key: data[i].choiceId,
                  label: data[i].choiceName,
                  disabled: !data[i].isDel
                });
              }
            }
          });
        this.getRequest("/questionShortAnswer/list?pageNum=1&pageSize=1000&courseId=" + row.courseId)
          .then(resp => {
            if (resp && resp.status == 200) {
              let data = resp.data.obj.list;
              for (let i = 0; i < data.length; i++) {
                this.sampleAnswerList.push({
                  key: data[i].shortAnswerId,
                  label: data[i].shortAnswerName,
                  disabled: !data[i].isDel
                });
              }
            }
          });
        this.getRequest("/questionJudge/list?pageNum=1&pageSize=1000&courseId=" + row.courseId)
          .then(resp => {
            if (resp && resp.status == 200) {
              let data = resp.data.obj.list;
              for (let i = 0; i < data.length; i++) {
                this.judgeList.push({
                  key: data[i].judgeId,
                  label: data[i].judgeName,
                  disabled: !data[i].isDel
                });
              }
            }
          });
      },
      showAutoGroupExamPaper(row) {

      },
      groupExamPaper(value, direction, movedKeys) {
        console.log(value, direction, movedKeys);
        console.log(this.checkedChoiceList);
      },
      autoGroupExamPaper() {

      },
      emptyExamPaperData() {
        this.examPaper = {
          paperId: '',
          paperName: ''
        }
      },
      addChoiceToExamPaper(){
        this.postJsonRequest("/examPaper/handleGroupExamPaper",
          {"paperId":this.handlingExamPaper.paperId,"questionType":"choice","questionIds":this.checkedChoiceList})
          .then(resp => {
            if (resp && resp.status == 200) {
              this.$message('成功添加'+resp.data.obj+'道题');
            }
          });
      },
      addJudgeToExamPaper(){
        this.postJsonRequest("/examPaper/handleGroupExamPaper",
          {"paperId":this.handlingExamPaper.paperId,"questionType":"judge","questionIds":this.checkedJudgeList})
          .then(resp => {
            if (resp && resp.status == 200) {
              this.$message('成功添加'+resp.data.obj+'道题');
            }
          });
      },
      addSampleAnswerToExamPaper(){
        this.postJsonRequest("/examPaper/handleGroupExamPaper",
          {"paperId":this.handlingExamPaper.paperId,"questionType":"sampleAnswer","questionIds":this.checkSampleAnswerList})
          .then(resp => {
            if (resp && resp.status == 200) {
              this.$message('成功添加'+resp.data.obj+'道题');
            }
          });
      },
      showExamPaper(){

      }
    }
  };
</script>
