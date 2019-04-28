<template>
  <div style="margin-top: 10px">
    <el-tabs v-model="defaultTab" type="border-card">
      <el-tab-pane label="单选题" name="menuManage">
        <radio-list></radio-list>
      </el-tab-pane>
      <el-tab-pane label="多选题" name="menuRoleConfig">
        <checkbox-list></checkbox-list>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
  import RadioChoice from './choice/RadioChoice.vue'
  import CheckboxChoice from './choice/CheckboxChoice.vue'
  export default {
    data() {
      return {
        defaultTab: 'RadioChoice'
      };
    },
    methods: {},
    components: {
      'radio-list': RadioChoice,
      'checkbox-list': CheckboxChoice
    }
  };
</script>
