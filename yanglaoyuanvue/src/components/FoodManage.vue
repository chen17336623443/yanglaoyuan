<template>
  <div class="maxDiv">
    <div class="headDiv">
      <el-row>
        <el-col :span="24">
          <el-col :span="20">
            <el-form :inline="true" class="demo-form-inline">
              <el-form-item label="食物类别">
                <el-select v-model="foodType" placeholder="请选择" clearable>
                  <el-option
                    v-for="f in foodtypeData"
                    :key="f.ftId"
                    :label="f.ftName"
                    :value="f.ftId"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="食物名称">
                <el-input v-model="foodName" placeholder="请输入食物名称" clearable />
              </el-form-item>
              <el-form-item label="食物描述">
                <el-input v-model="foodRemark" placeholder="请输入食物描述" clearable />
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="4">
            <el-button @click="isComQuery=true,loadData()">查询</el-button>
            <el-button @click="readyInsert">新增</el-button>
          </el-col>
        </el-col>
      </el-row>
    </div>
    <div class="bodyDiv">
      <div style="width: 100%;height: 460px;" class="scrollbar">
        <el-scrollbar style="height: 100%;">
          <el-card
            :body-style="{ padding: '0px' }"
            v-for="f in foodData"
            :key="f.fId"
            class="cards"
          >
            <img :src="'../../static/imgs/'+f.fImg" class="image" />
            <div style="padding: 14px;">
              <p style="text-align:center;">
                <el-popover placement="top" width="160" trigger="hover" :content="f.fRemark">
                  <el-tag style="cursor:pointer;" slot="reference">{{f.fName}}</el-tag>
                </el-popover>
                <el-tag type="warning">{{f.fPrice}}元</el-tag>
              </p>
              <div class="bottom clearfix">
                <el-button
                  type="text"
                  @click="readyUpdate(f)"
                  class="button"
                  icon="el-icon-edit"
                  style="color: #E6A23C;font-size:12px;"
                >修改</el-button>
                <el-button
                  type="text"
                  class="button"
                  icon="el-icon-delete"
                  style="color: #F56C6C;font-size:12px;"
                >删除</el-button>
              </div>
            </div>
          </el-card>
        </el-scrollbar>
      </div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="current"
        :page-sizes="[6, 12]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </div>
    <el-dialog :visible.sync="foodDialog" width="50%">
      <span slot="title">
        <i :class="isAdd?'el-icon-plus':'el-icon-edit'"></i>
        {{isAdd?'食物新增':'食物修改'}}
      </span>
      <el-form :model="food" label-width="60px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称：">
              <el-input v-model="food.fName" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类别：">
              <el-select v-model="food.foodtypeByFtId.ftId">
                <el-option
                  v-for="f in foodtypeData"
                  :key="f.ftId"
                  :label="f.ftName"
                  :value="f.ftId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="价格：">
              <el-input v-model.number="food.fPrice" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="辣度：">
              <el-select v-model="food.fPiquancy">
                <el-option v-for="l in ladu" :key="l.id" :label="l.value" :value="l.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="口味：">
              <el-select v-model="food.fTaste">
                <el-option v-for="k in kouwei" :key="k.id" :label="k.value" :value="k.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="图片：">
              <el-upload
                class="avatar-uploader"
                ref="upload"
                :action="actionUrl"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :auto-upload="false"
                :before-upload="beforeAvatarUpload"
              >
                <img v-if="food.fImg" :src="'../../static/imgs/'+food.fImg" class="avatar" />
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="描述：">
              <el-input type="textarea" v-model="food.fRemark" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="禁忌：">
              <el-checkbox-group v-model="taboocrowds">
                <el-checkbox v-for="t in taboData" :label="t.tcId" :key="t.tcId">{{t.tcName}}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="foodDialog = false">取 消</el-button>
        <el-button type="primary" @click="updateFood">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      current: 1,
      pageSize: 6,
      total: 0,
      foodData: [],
      foodtypeData: [],
      taboData: [],
      foodType: "",
      foodName: "",
      foodRemark: "",
      foodDialog: false,
      isAdd: true,
      food: {
        foodtypeByFtId: {},
      },
      ladu: [
        { id: 1, value: "不辣" },
        { id: 2, value: "微辣" },
        { id: 3, value: "中辣" },
        { id: 4, value: "特辣" },
      ],
      kouwei: [
        { id: 1, value: "辣味" },
        { id: 2, value: "甜味" },
        { id: 3, value: "淡味" },
        { id: 4, value: "咸味" },
        { id: 5, value: "酸味" },
        { id: 6, value: "苦味" },
        { id: 7, value: "涩味" },
        { id: 8, value: "其味无穷" },
      ],
      isComQuery: false,
      Comnumber: 0,
      taboocrowds: [],
      actionUrl:'',
    };
  },
  methods: {
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.loadData();
    },
    handleCurrentChange(current) {
      this.current = current;
      this.loadData();
    },
    loadData() {
      let that = this;
      //查询所有食物类别
      this.$axios.get("http://localhost:8089/food/alltype").then((r) => {
        that.foodtypeData = r;
      });
      //查询所有禁忌人群
      this.$axios.get("http://localhost:8089/food/tabAll").then((r) => {
        this.taboData = r;
      });

      if (!this.isComQuery) {
        this.$axios
          .get(
            "http://localhost:8089/food/all?pageNo=" +
              this.current +
              "&pageSize=" +
              this.pageSize
          )
          .then((r) => {
            console.log(r);
            that.foodData = r.list;
            that.total = r.total;
          });
      } else {
        if (
          this.foodType.length < 1 &&
          this.foodName.length < 1 &&
          this.foodRemark.length < 1
        ) {
          this.Comnumber = 0;
          that.isComQuery = false;
          this.$message.warning("至少需要一个条件！(已为您查询所有)");
          this.loadData();
          return;
        }
        this.Comnumber++; //组合查询次数+1
        this.current = this.Comnumber > 1 ? this.current : 1;
        let param = { pageNo: this.current, pageSize: this.pageSize };
        if (this.foodType != null && this.foodType > 0)
          param.ft_id = this.foodType;
        if (this.foodName.length > 0) param.f_name = this.foodName;
        if (this.foodRemark.length > 0) param.f_remark = this.foodRemark;
        console.log("即将查询的条件是：", param);
        this.$axios.get("food/comQuery", { params: param }).then((r) => {
          console.log("组合查询响应数据：", r);
          this.foodData = r.list;
          this.total = r.total;
        });
      }
    },
    //准备修改
    readyUpdate(food) {
      this.food = { ...food };
      this.isAdd = false;
      this.foodDialog = true;
      this.food.taboocrowds.forEach((t) => {
        this.taboocrowds.push(t.tcId);
      });
    },
    handleAvatarSuccess(res, file) {
      console.log(res);
      console.log(file);
      console.log(URL.createObjectURL(file.raw));
    },
    beforeAvatarUpload(file) {
        return new Promise((resolve, reject) => {
          this.$nextTick(() => {
            this.actionUrl = 'http://localhost:8089/food/updateImg?f_id='+this.food.fId;       
            resolve()
          })
        })
      
    },
    
    //修改食物/新增食物
    updateFood() {
      //先把禁忌人群数组设置为空
      this.food.taboocrowds = [];
      //把选中的禁忌人群存入将要修改/新增的对象中
      this.taboData.forEach((t) => {
        this.taboocrowds.forEach((tc) => {
          if (tc == t.tcId) {
            this.food.taboocrowds.push(t);
          }
        });
      });
      let url = this.isAdd ? "food/insert" : "food/update";
      this.$axios.post(url, this.food).then((r) => {
        if ((r) => 0) {
          if (this.isAdd) {
            //新增返回食物id，用于上传食物图片
            this.$set(this.food, "fId", r);         
          }
          this.$refs.upload.submit();
          this.$message.success(this.isAdd ? "新增成功！" : "修改成功！");
          this.loadData();
          this.foodDialog = false;
        } else {
          this.$message.success(this.isAdd ? "新增失败！" : "修改失败！");
        }
      });
    },
    //组合查询
    comQuery() {},
    //准备新增
    readyInsert() {
      this.food = { foodtypeByFtId: {} };
      this.foodDialog = true;
      this.isAdd = true;
      this.taboocrowds = [];
    },
  },
  created() {
    this.loadData();
  },
};
</script>

<style scoped>
.maxDiv {
  overflow: hidden;
  overflow-y: auto;
  -webkit-overflow-scrolling: touch;
  background: #dcdcdc36;
  height: 100%;
  padding: 0 10px;
}
.headDiv {
  width: 98.2%;
  height: 40px;
  border-radius: 10px;
  background: #fff;
  box-shadow: 0px 0px 5px 5px gainsboro;
  margin-top: 10px;
  padding: 10px;
}
.bodyDiv {
  width: 98.2%;
  height: 485px;
  border-radius: 10px;
  background: #fff;
  box-shadow: 0px 0px 5px 5px gainsboro;
  margin-top: 10px;
  padding: 10px;
}
.headDiv .el-form-item {
  margin-bottom: 10px;
}
.cards {
  width: 170px;
  height: 255px;
  float: left;
  margin: 0 10px 10px 0;
  box-shadow: 0px 0px 5px 5px gainsboro;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
  text-align: right;
}
.bottom .el-button + .el-button {
  margin-left: 0;
}
.button {
  padding: 0;
}

.image {
  width: 170px;
  height: 170px;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
/*滚动条样式*/
.scrollbar .el-scrollbar__wrap {
  overflow: scroll;
  width: 110%;
  height: 100%;
  overflow-x: hidden !important;
}
</style>