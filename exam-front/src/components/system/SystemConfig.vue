<template>
  <div style="margin-top: 10px">
    <el-tabs v-model="defaultTab" type="card">
      <el-tab-pane label="菜单管理" name="menuManage">
        <menu-manage></menu-manage>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
  import MenuManager from './basic/MenuManage.vue'
  export default {
    data() {
      return {
        defaultTab: 'menuManage'
      };
    },
    methods: {},
    components: {
      'menu-manager': MenuManager,
    }
  };
</script>
