<template>
  <el-container>
    <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
      <div style="display: inline">
        <el-input
          placeholder="通过学院名称搜索学院,记得回车哦..."
          clearable
          @change="keyWordsChange"
          style="width: 300px;margin: 0px;padding: 0px;"
          size="mini"
          :disabled="advanceSearchViewVisible"
          @keyup.enter.native="searchAcademy"
          prefix-icon="el-icon-search"
          v-model="keyWords">
        </el-input>
        <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchAcademy">搜索
        </el-button>
      </div>
      <div style="margin-left: 5px;margin-right: 20px;display: inline">
        <el-button type="primary" size="mini" icon="el-icon-plus"
                   @click="showAddAcademyView">
          添加
        </el-button>
      </div>
    </el-header>
    <el-main>
      <div>
        <el-radio v-model="radio1" label="1" border>备选项1</el-radio>
        <el-radio v-model="radio1" label="2" border>备选项2</el-radio>
      </div>
    </el-main>
  </el-container>

</template>

<script>
  export default {
    data () {
      return {
        radio1: '1',
        radio2: '1',
        radio3: '1',
        radio4: '1'
      };
    }
  }
</script>
