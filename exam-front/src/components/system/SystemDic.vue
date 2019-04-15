<template>
  <div style="margin-top: 10px">
    <el-tabs v-model="defaultTab" type="card">
      <el-tab-pane label="字典类型" name="dicType">
        <dic-type-manage></dic-type-manage>
      </el-tab-pane>
      <el-tab-pane label="字典键值" name="dic">
        <dic-manage></dic-manage>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
  import Dic from './dict/Dic.vue'
  import DicType from "./dict/DicType";
  export default {
    data() {
      return {
        defaultTab: 'dicType'
      };
    },
    methods: {},
    components: {
      'dic-manage': Dic,
      'dic-type-manage': DicType
    }
  };
</script>
