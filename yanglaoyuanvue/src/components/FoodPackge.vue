<template>
  <el-container class="container">
    <el-aside class="aside">
        <el-row>
            <el-col :span="24">
                <el-button>新增</el-button>
            </el-col>
        </el-row>
        
        <el-form>
            <el-row v-for="fp in foodPackgeData" :key="fp.fpId">
                <el-col :span="24">
                    <el-form-item>
                        {{fp.fpName}}
                    </el-form-item>
                </el-col>
            </el-row>
            
        </el-form>
        
    </el-aside>
    <el-main class="main"></el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {

        foodPackgeData:[]
    }
  },
  methods:{
      loadData(){
          this.$axios.get("http://localhost:8089/foodpackge/all")
          .then(r=>{
              console.log(r);
              this.foodTypeData=r;
          })
      }
  },created(){
      this.loadData();
  }
};
</script>

<style scoped>
.container {
  background: #dcdcdc36;
  width: 100%;
  height: 100%;
  padding: 0 10px;
}
.aside {
  background: #fff;
  box-shadow: 0px 0px 5px 5px gainsboro;
  width: 130px;
  height: 97%;
  margin-top: 10px;
  padding: 10px;
}
.main {
  background: #fff;
  box-shadow: 0px 0px 5px 5px gainsboro;
  width: 670px;
  height: 97%;
  margin-top: 10px;
  padding: 10px;
}
</style>