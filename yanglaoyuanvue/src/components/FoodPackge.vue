<template>
  <el-container class="container">
    <el-aside class="aside" width="230px">
      <el-row>
        <el-col :span="24" style="text-align:center;">
          <el-button type="text" icon="el-icon-potato-strips" style="color:red">食物套餐</el-button>
        </el-col>
      </el-row>

      <el-form>
        <el-row>
          <el-col :span="24">
            <el-form-item v-for="fp in foodPackgeData" :key="fp.fpId">
              <el-button
                type="text"
                @click="isAdd=false,openFoodpackge(fp)"
                :style="{'background': '#A8CCDC','width': '100%','color': checks==fp.fpId?'yellow':'#fff'}"
              >{{fp.fpName}}</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-aside>
    <el-main class="main">
      <el-tabs type="border-card" style="height:99.7%;">
        <el-tab-pane label="套餐信息">
          <el-form :model="foodpackge" label-width="100px" style="width:100%">
            <el-row>
              <el-col :span="14">
                <el-form-item label="套餐名称：">
                  <el-input v-model="foodpackge.fpName" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="14">
                <el-form-item label="套餐价格：">
                  <el-input v-model.number="foodpackge.fpPrice" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="14">
                <el-form-item label="套餐描述：">
                  <el-input type="textarea" v-model="foodpackge.fpRemark" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="14" style="text-align:right;">
                <el-button @click="cancleUpdate()">取消</el-button>
                <el-button @click="updateFoodpackge()">{{isAdd?'新增':'修改'}}</el-button>
                
              </el-col>
            </el-row>
          </el-form>
        </el-tab-pane>
        
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      foodPackgeData: [],
      foodpackge: {},
      checks: 0,
      actionUrl: "",
      foodweekData:{
        week:['星期一','星期二','星期三','星期四','星期五','星期六','星期日']
      },
      isAdd:true,
      
    };
  },
  methods: {
    handleAvatarSuccess(e) {
      this.$set(this.foodpackge,'fpId',e.url);
      this.loadData();
    },
    beforeAvatarUpload(file) {
      return new Promise((resolve, reject) => {
        this.$nextTick(() => {
          this.actionUrl ="http://localhost:8089/foodpackge/updateImg?fp_id="+this.foodpackge.fpId;
          resolve();
        });
      });
    },
    //加载数据
    loadData() {
      this.$axios.get("http://localhost:8089/foodpackge/all").then((r) => {
        console.log(r);
        this.foodPackgeData = r;
      });
    },
    //取消操作
    cancleUpdate(){
        this.foodpackge={};
        this.isAdd=true;
    },
    // 新增套餐/修改套餐
    updateFoodpackge(){
        console.log("isAdd=",this.isAdd);
        let url=this.isAdd?"http://localhost:8089/foodpackge/insert":"http://localhost:8089/foodpackge/update";
        if(this.foodpackge.fpName.length<1){
            this.$message.warning("请输入套餐名称！");
            return;
        }
        if(this.foodpackge.fpPrice=="" || this.foodpackge.fpPrice<1){
            this.$message.warning("请输入套餐价格！");
            return;
        }
        if(this.foodpackge.fpRemark.length<1){
            this.$message.warning("请输入套餐描述！");
            return;
        }
        console.log("即将提交的数据：",this.foodpackge);
        this.$axios.post(url,this.foodpackge)
        .then(r=>{
            if(r>0){
                this.loadData();
                this.$message.success(this.isAdd?'新增成功！':'修改成功！');
                this.cancleUpdate();
            }else{
                this.$message.warning(this.isAdd?'新增失败！':'修改失败！');
            }
        })
    },
    //套餐列表点击
    openFoodpackge(foodpackge) {
      this.foodpackge = foodpackge;
      this.checks = foodpackge.fpId;
      this.queryFoodweek(foodpackge.fpId);
    },
    //查询该套餐的食物周次
    queryFoodweek(fpId){
      this.$axios.get("http://localhost:8089/foodweek/allByFpid?fp_id="+fpId)
      .then(r=>{
        r.forEach(fw => {
          
        });
          this.foodweekData=r;
          console.log("该套餐的食物周次为：",this.foodweekData);
      })
    }
  },
  created() {
    this.loadData();
  },
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
  height: 97%;
  margin-top: 10px;
  padding: 10px;
}
.main {
  background: #fff;
  box-shadow: 0px 0px 5px 5px gainsboro;
  width: 690px;
  height: 97%;
  margin-top: 10px;
  padding: 10px;
}
.packges {
  color: yellow;
}

.avatar,.avatar-uploader {
  width: 178px;
  height: 178px;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
</style>