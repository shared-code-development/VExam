<template>
  <div>
    <div style="text-align: left">
      <el-input
        placeholder="输入字典名称搜索..."
        style="width: 500px;margin: 0px;padding: 0px;"
        size="mini"
        prefix-icon="el-icon-search"
        v-model="keywords">
      </el-input>
      <el-button type="primary" size="mini" icon="el-icon-plus"
                 @click="showAddDicView">
        添加
      </el-button>
    </div>
    <div>
      <el-tree
        :props="defaultProps"
        :data="dicTreeList"
        ref="tree"
        :filter-node-method="filterNode"
        v-loading="treeLoading"
        style="width: 500px;margin-top: 10px"
        :render-content="renderContent">
      </el-tree>
      <div style="text-align: left">
        <el-dialog
          title="添加字典"
          :visible.sync="dialogVisible"
          width="25%">
          <div>
            <span>上级字典</span>
            <el-select v-model="dic.parentDicId" style="width: 230px" placeholder="请选择" size="mini">
              <el-option
                v-for="item in parentDicList"
                :key="item.dicId"
                :label="item.dicValue"
                :value="item.dicId"
                :disabled="!item.isDel"
              >
              </el-option>
            </el-select>
          </div>
          <div>
            <span>字典类型</span>
            <el-select v-model="dic.dicTypeId" style="margin-top:10px;width: 230px" placeholder="请选择" size="mini">
              <el-option
                v-for="item in dicTypeList"
                :key="item.dicTypeId"
                :label="item.dicTypeName"
                :value="item.dicTypeId"
                :disabled="!item.isDel"
              >
              </el-option>
            </el-select>
          </div>
          <div style="margin-top: 10px">
            <span>字典key</span>
            <el-input size="mini" style="width: 230px;" v-model="dic.dicKey" placeholder="请输入字典key..."></el-input>
          </div>
          <div style="margin-top: 10px">
            <span>字典value</span>
            <el-input size="mini" style="width: 200px;" v-model="dic.dicValue" placeholder="请输入字典value..."></el-input>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button size="small" @click="dialogVisible = false">取消</el-button>
            <el-button size="small" type="primary" @click="addDic">添加</el-button>
          </span>
        </el-dialog>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        keywords: '',
        treeLoading: false,
        dialogVisible: false,
        parentDic: {},
        parentDicList: [],
        dicTypeList: [],
        dicTreeList: [],
        defaultProps: {
          label: 'dicName',
          isLeaf: 'leaf',
          children: 'children'
        },
        dic: {
          dicId:'',
          parentDicId:'',
          dicTypeId: '',
          dicKey:'',
          dicValue:''
        }
      }
    },
    mounted: function () {
      this.treeLoading = true;
      this.loadDicTreeData();
      this.initData();
    },
    watch: {
      keywords(val) {
        this.$refs.tree.filter(val);
      }
    },
    methods: {
      filterNode(value, data) {
        if (!value) return true;
        return data.name.indexOf(value) !== -1;
      },
      initData(){
        this.getRequest("/dic/type/list?pageNum=1&pageSize=100").then(response => {
          if (response && response.status == 200) {
            this.dicTypeList = response.data.obj.list;
          }
        });
      },
      loadDicTreeData() {
        let _this = this;
        this.getRequest("/dic/type/tree?parentId=").then(resp => {
          _this.treeLoading = false;
          if (resp && resp.status == 200) {
            _this.dicTreeList = resp.data.obj;
          }
        })
      },
      setDataToTree(treeData, pId, respData) {
        for (let i = 0; i < treeData.length; i++) {
          let td = treeData[i];
          if (td.dicId == pId) {
            treeData[i].children = treeData[i].children.concat(respData);
            return;
          } else {
            this.setDataToTree(td.children, pId, respData);
          }
        }
      },
      addDic() {
        let _this = this;
        this.dialogVisible = false;
        this.treeLoading = true;
        this.postRequest("/dic", this.dic).then(resp => {
          _this.treeLoading = false;
          if (resp && resp.status == 200) {
            let respData = resp.data;
            _this.setDataToTree(_this.dicTreeList, _this.parentDic.dicId, respData.msg)
          }
        })
      },
      loadParentDic(DicId) {
        let _this = this;
        _this.getRequest("/dic/list?pageSize=20").then(resp => {
          debugger
          if (resp && resp.status == 200) {
            _this.parentDicList = resp.data.obj.list;
          }
        });
      },
      showAddDicView(data, event) {
        // this.parentDic = data
        // this.loadParentDic(data.parentId);
        this.dialogVisible = true;
      },
      deleteDic(data, event) {
        let _this = this;
        if (data.children.length > 0) {
          this.$message({
            message: '该菜单下尚有其他菜单，不能被删除!',
            type: 'warning'
          });
        } else {
          this.$confirm('删除[' + data.name + ']菜单, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.treeLoading = true;
            _this.deleteRequest("/system/config/Dic/" + data.DicId).then(resp => {
              _this.treeLoading = false;
              if (resp && resp.status == 200) {
                _this.deleteLocalDic(_this.treeData, data);
              }
            });
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        }
        event.stopPropagation()
      },
      deleteLocalDic(treeData, data) {
        for (let i = 0; i < treeData.length; i++) {
          let td = treeData[i];
          if (td.DicId == data.DicId) {
            treeData.splice(i, 1);
            return;
          } else {
            this.deleteLocalDic(td.children, data);
          }
        }
      },
      renderContent(h, {node, data, store}) {
        return (<span style="flex: 1; display: flex; align-items: center; justify-content: space-between; font-size: 14px; padding-right: 8px;">
          <span><span>{node.label}</span></span>
        <span>
        <el-button style="font-size: 12px;" type="primary" size="mini" style="padding:3px" on-click={ () => this.showAddDicView(data,event) }>添加字典</el-button>
        <el-button style="font-size: 12px;" type="danger" size="mini" style="padding:3px" on-click={ () => this.deleteDic(data,event) }>删除字典</el-button>
        </span>
        </span>);
      }
    }
  };
</script>
