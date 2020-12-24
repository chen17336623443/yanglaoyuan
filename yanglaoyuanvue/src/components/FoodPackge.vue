<template>
  <el-container class="container">
    <el-aside class="aside" width="230px">
      <el-row>
        <el-col :span="24" style="text-align:right;">
          <el-button type="text" icon="el-icon-plus">新增</el-button>
        </el-col>
      </el-row>

      <el-form>
        <el-row>
          <el-col :span="24">
            <el-form-item v-for="fp in foodPackgeData" :key="fp.fpId">
              <el-button
                type="text"
                @click="openFoodpackge(fp)"
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
                <el-form-item label="套餐图片：">
                  <el-upload
                    class="avatar-uploader"
                    ref="upload"
                    :action="actionUrl"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :auto-upload="false"
                    :before-upload="beforeAvatarUpload"
                  >
                    <img
                      v-if="foodpackge.fpImg"
                      :src="'../../static/imgs/'+foodpackge.fpImg"
                      class="avatar"
                    />
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
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
                <el-button >取消</el-button>
                <el-button >修改</el-button>
              </el-col>
            </el-row>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="套餐配置">
          <el-table :data="foodweekData" style="width:100%" border>
            <el-table-column v-for="w in week" :label="w" :key="w" fixed></el-table-column>
          </el-table>
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
      foodweekData:[],
      week:['星期一','星期二','星期三','星期四','星期五','星期六','星期日']
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      console.log(res);
      console.log(file);
      console.log(URL.createObjectURL(file.raw));
    },
    beforeAvatarUpload(file) {
      return new Promise((resolve, reject) => {
        this.$nextTick(() => {
          this.actionUrl =
            "http://localhost:8089/foodpackge/updateImg?fp_id=" +
            this.foodpackge.fpId;
          resolve();
        });
      });
    },
    loadData() {
      this.$axios.get("http://localhost:8089/foodpackge/all").then((r) => {
        console.log(r);
        this.foodPackgeData = r;
      });
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