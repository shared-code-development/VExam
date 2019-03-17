<template>
  <div style="margin-top: 10px">
    <el-tabs v-model="defaultTab" type="card">
      <el-tab-pane label="菜单配置" name="menuManage">
        <menu-manage></menu-manage>
      </el-tab-pane>
      <el-tab-pane label="角色权限配置" name="menuRoleConfig">
        <menu-role-config></menu-role-config>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
  import MenuManage from './config/MenuManage.vue'
  import MenuRoleConfig from './config/MenuRoleConfig.vue'
  export default {
    data() {
      return {
        defaultTab: 'menuManage'
      };
    },
    methods: {},
    components: {
      'menu-manage': MenuManage,
      'menu-role-config': MenuRoleConfig,
    }
  };
</script>
