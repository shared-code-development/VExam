<template>
  <div>
    <div style="text-align: left">
      <el-input
        placeholder="输入菜单名称搜索菜单..."
        style="width: 500px;margin: 0px;padding: 0px;"
        size="mini"
        prefix-icon="el-icon-search"
        v-model="keywords">
      </el-input>
    </div>
    <div>
      <el-tree
        :props="defaultProps"
        :data="treeData"
        ref="tree"
        :filter-node-method="filterNode"
        v-loading="treeLoading"
        style="width: 500px;margin-top: 10px"
        :render-content="renderContent">
      </el-tree>
      <div style="text-align: left">
        <el-dialog
          title="添加菜单"
          :visible.sync="dialogVisible"
          width="25%">
          <div>
            <span>上级菜单</span>
            <el-select v-model="menuTree" style="width: 200px" placeholder="请选择" size="mini">
              <el-option
                v-for="item in allMenu"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </div>
          <div style="margin-top: 10px">
            <span>菜单名称</span>
            <el-input size="mini" style="width: 200px;" v-model="menuName" placeholder="请输入菜单名称..."
                      @keyup.enter.native="addMenu"></el-input>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button size="small" @click="dialogVisible = false">取消</el-button>
            <el-button size="small" type="primary" @click="addMenu">添加</el-button>
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
        menuName: '',
        treeLoading: false,
        dialogVisible: false,
        allMenu: [],
        menuTree: '',
        treeData: [],
        defaultProps: {
          label: 'name',
          isLeaf: 'leaf',
          children: 'children'
        }
      }
    },
    mounted: function () {
      this.treeLoading = true;
      this.loadTreeData();
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
      loadTreeData() {
        debugger
        var _this = this;
        this.getRequest("/system/config/menu/0").then(resp => {
          _this.treeLoading = false;
          if (resp && resp.status == 200) {
            _this.treeData = resp.data;
          }
        })
      },
      setDataToTree(treeData, pId, respData) {
        debugger
        for (var i = 0; i < treeData.length; i++) {
          var td = treeData[i];
          if (td.id == pId) {
            treeData[i].children = treeData[i].children.concat(respData);
            return;
          } else {
            this.setDataToTree(td.children, pId, respData);
          }
        }
      },
      addMenu() {
        var _this = this;
        this.dialogVisible = false;
        this.treeLoading = true;
        this.postRequest("/system/config/menu", {
          name: this.menuName,
          parentId: this.menuTree
        }).then(resp => {
          debugger
          _this.treeLoading = false;
          if (resp && resp.status == 200) {
            var respData = resp.data;
            _this.name = '';
            _this.setDataToTree(_this.treeData, _this.parentMenu, respData.msg)
          }
        })
      },
      loadAllMenu() {
        var _this = this;
        this.getRequest("/system/config/menu/list").then(resp => {
          if (resp && resp.status == 200) {
            _this.allMenu = resp.data.obj;
          }
        });
      },
      showAddMenuView(data, event) {
        debugger
        this.loadAllMenu();
        this.dialogVisible = true;
        this.parentMenu = data.id;
        event.stopPropagation()
      },
      deleteMenu(data, event) {
        debugger
        let _this = this;
        if (data.children.length > 0) {
          this.$message({
            message: '该菜单下尚有其他部门，不能被删除!',
            type: 'warning'
          });
        } else {
          this.$confirm('删除[' + data.name + ']菜单, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.treeLoading = true;
            _this.deleteRequest("/system/config/menu/" + data.id).then(resp => {
              _this.treeLoading = false;
              if (resp && resp.status == 200) {
                var respData = resp.data;

                _this.deleteLocalMenu(_this.treeData, data);
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
      deleteLocalMenu(treeData, data) {
        for (var i = 0; i < treeData.length; i++) {
          var td = treeData[i];
          if (td.id == data.id) {
            treeData.splice(i, 1);
            return;
          } else {
            this.deleteLocalMenu(td.children, data);
          }
        }
      },
      renderContent(h, {node, data, store}) {
        return (
          <span style="flex: 1; display: flex; align-items: center; justify-content: space-between; font-size: 14px; padding-right: 8px;">
            <span>
              <span>{node.label}</span>
            </span>
          <span>
            <el-button style="font-size: 12px;" type="primary" size="mini" style="padding:3px" on-click={ () => this.showAddMenuView(data,event) }>添加菜单</el-button>
            <el-button style="font-size: 12px;" type="danger" size="mini" style="padding:3px" on-click={ () => this.deleteMenu(data,event) }>删除菜单</el-button>
          </span>
        </span>);
      }
    }
  };
</script>
