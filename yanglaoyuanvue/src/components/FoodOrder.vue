<template>
  <div class="maxDiv">
    <div style="background:#fff;box-shadow: 0px 0px 5px 5px gainsboro;padding:3px 10px;margin-top:10px;">
      <el-form
        style="width:100%;margin-top:10px;"
        class="headForm"
      >
        <el-row>
          <el-col :span="12">
            <el-form-item label="客户：">
              <el-select
                v-model="oldmanId"
                clearable
                placeholder="请选择客户"
              >
                <el-option
                  v-for="o in oldmanData"
                  :key="o.omId"
                  :label="o.tomName"
                  :value="o.omId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col
            :span="12"
            style="text-align:right;padding-right:10px;"
          >
            <el-badge
              :value="chooseTotalCount>0?chooseTotalCount:''"
              class="item"
            >
              <el-button
                @click="goCart"
                icon="el-icon-shopping-bag-1"
              >已点菜单</el-button>
            </el-badge>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="辣度：">
              <el-select
                v-model="ladu"
                clearable
              >
                <el-option
                  v-for="l in ladus"
                  :key="l.id"
                  :label="l.value"
                  :value="l.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="口味：">
              <el-select
                v-model="kouwei"
                clearable
              >
                <el-option
                  v-for="l in kouweis"
                  :key="l.id"
                  :label="l.value"
                  :value="l.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="价格：">
              <el-input
                v-model="minPrice"
                style="width:100px"
              />-
              <el-input
                v-model="maxPrice"
                style="width:100px"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="禁忌人群：">
              <el-checkbox-group v-model="taboocrowds">
                <el-checkbox
                  v-for="t in taboData"
                  :label="t.tcId"
                  :key="t.tcId"
                >{{t.tcName}}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-button @click="groupQuery">查询</el-button>
          </el-col>
        </el-row>
      </el-form>
      <el-container>
        <el-aside width="180px">
          <ul class="ulstyle">
            <li
              v-for="f in foodType"
              :key="f.ftId"
              @click="chooseFoodType(f.ftId)"
            >{{f.ftName}}</li>
          </ul>
        </el-aside>
        <el-main>
          <div
            style="width: 100%;height: 410px;"
            class="scrollbar"
          >
            <el-scrollbar style="height: 100%;">
              <el-card
                :body-style="{ padding: '0px' }"
                v-for="f in foodDatac"
                :key="f.fId"
                class="cards"
              >
                <img
                  :src="'../../static/imgs/'+f.fImg"
                  class="image"
                />
                <div style="padding: 14px;">
                  <p style="text-align:center;">
                    <el-popover
                      placement="top"
                      width="160"
                      trigger="hover"
                      :content="f.fRemark"
                    >
                      <el-tag
                        style="cursor:pointer;"
                        slot="reference"
                      >{{f.fName}}</el-tag>
                    </el-popover>
                    <el-tag type="warning">{{f.fPrice}}元</el-tag>
                  </p>
                  <div class="bottom clearfix">
                    <el-input-number
                      v-model="numArray[foodDatac.indexOf(f)]"
                      @change="handleChange"
                      :min="0"
                      :max="10"
                      label="描述文字"
                      size="small"
                      style="margin-top:10px"
                    ></el-input-number>
                    <el-badge
                        :value="existCount(f.fId)>0?existCount(f.fId):''"
                        class="item"
                    >
                        <el-button
                        type="text"
                        icon="el-icon-shopping-cart-2"
                        style="color: #E6A23C;font-size:12px;"
                        @click="addCart(f,numArray[foodDatac.indexOf(f)])"
                        >加入</el-button>
                    </el-badge>
                    
                  </div>
                </div>
              </el-card>
            </el-scrollbar>
          </div>
        </el-main>
      </el-container>
    </div>
    <el-dialog
      title="已选菜单"
      :visible.sync="cartDialog"
      width="60%"
    >
      <el-row>
        <el-col :span="24">
          餐次：
          <el-select
            v-model="checkedMelas"
            clearable
            placeholder="请选择"
          >
            <el-option
              v-for="m in melas"
              :key="m.id"
              :label="m.value"
              :value="m.value"
            ></el-option>
          </el-select>
        </el-col>
      </el-row>
      <el-table
        :data="chooseFood"
        border
        :summary-method="getSummaries"
        show-summary
      >
        <el-table-column
          prop="fName"
          label="食品名称"
        ></el-table-column>
        <el-table-column
          prop="fPrice"
          label="食品价格"
        ></el-table-column>
        <el-table-column
          prop="count"
          label="选购数量"
        ></el-table-column>
        <el-table-column
          prop="priceTotal"
          label="小计"
        ></el-table-column>
        <el-table-column
          label="操作"
          class="caozuo"
        >
          <template slot-scope="scope">
            <el-input-number
              v-model="scope.row.count"
              @change="handleCartChange(scope.row)"
              :min="1"
              :max="10"
              label="描述文字"
              size="small"
            ></el-input-number>
            <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              :title="'确定要将'+scope.row.fName+'移出吗？'"
              @confirm="deleteFood(scope.row)"
            >
              <el-button
                slot="reference"
                icon="el-icon-delete"
                type="text"
                style="color: #F56C6C;"
              >删除</el-button>
            </el-popconfirm>

          </template>
        </el-table-column>
      </el-table>
      <el-row>
        <el-col
          :span="24"
          style="text-align:center"
        >
          <el-button @click="submitFood">提交</el-button>
        </el-col>
      </el-row>
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
      //所有食物数据
      foodData: [],
      //copy所有食物数据
      foodDatac: [],
      //所有老人
      oldmanData: [],
      //禁忌人群
      taboData: [],
      //老人id
      oldmanId: "",
      ladu: "",
      kouwei: "",
      ladus: [
        { id: 1, value: "不辣" },
        { id: 2, value: "微辣" },
        { id: 3, value: "中辣" },
        { id: 4, value: "特辣" },
      ],
      //口味数组
      kouweis: [
        { id: 1, value: "辣味" },
        { id: 2, value: "甜味" },
        { id: 3, value: "淡味" },
        { id: 4, value: "咸味" },
        { id: 5, value: "酸味" },
        { id: 6, value: "苦味" },
        { id: 7, value: "涩味" },
        { id: 8, value: "其味无穷" },
      ],
      //最低价格
      minPrice: "",
      //最高价格
      maxPrice: "",
      //已选择的禁忌人群
      taboocrowds: [],
      //所有食物类型
      foodType: [],
      //已选择至购物车中的数据
      chooseFood: [],
      chooseTotalCount: 0,
      //购物车对话框
      cartDialog: false,
      //餐次
      melas: [
        { id: 1, value: "早餐" },
        { id: 2, value: "午餐" },
        { id: 3, value: "晚餐" },
      ],
      //所选餐次
      checkedMelas: "",
      //点餐对象
      foodorder: {
        //点餐老人
        oldmanByOmId: {},
        //点餐详情
        foodorderdetails: [],
      },
      //初始化食物数量
      numArray: [],
    };
  },
  methods: {
    handleChange(change) {
      console.log(change);
    },
    //购物车中食物数量发生变化触发
    handleCartChange(food) {
      food.priceTotal=food.count*food.fPrice;
      //更新数据/视图
      this.$set(this.chooseFood, this.chooseFood.indexOf(food), food);
      //购物车食物总数量 重新赋值
      this.chooseTotalCount = 0;
      this.chooseFood.forEach((c) => {
        this.chooseTotalCount += c.count;
      });
      //调用查询/更新某一个商品加入购物的数量
      this.existCount(food.fId);
    },
    loadFoodData() {
      this.$axios.get("http://localhost:8089/food/allNotPage").then((r) => {
        this.foodData = r;
        this.foodDatac = []; //copy数组
        this.numArray = [];
        this.foodData.forEach((f) => {
          f.count = 0;
          f.priceTotal = 0;
          this.foodDatac.push(f);
          this.numArray.push(0);
        });
        console.log("所有食物：", this.foodDatac);
      });
    },
    //加载数据
    loadData() {
      //查询老人
      this.$axios.get("http://localhost:8089/oldman/selectAll").then((r) => {
        this.oldmanData = r;
      });
      //查询禁忌人群
      this.$axios.get("http://localhost:8089/food/tabAll").then((r) => {
        this.taboData = r;
      });
      //查询所有食物类别
      this.$axios.get("http://localhost:8089/food/alltype").then((r) => {
        this.foodType = r;
      });
    },
    //组合查询
    groupQuery() {
      if (
        this.ladu.length < 1 &&
        this.kouwei.length < 1 &&
        this.taboocrowds.length < 1 &&
        this.minPrice == "" &&
        this.maxPrice == ""
      ) {
        this.$message.warning("请至少输入一个条件！(已为您查询所有)");
        this.loadFoodData();
        return;
      }
      let param = {};
      if (this.ladu.length > 0) param.fPiquancy = this.ladu;
      if (this.kouwei.length > 0) param.fTaste = this.kouwei;
      if (this.taboocrowds.length > 0)
        param.taboocrowds = this.taboocrowds.join(",");
      if (
        this.minPrice != null &&
        this.minPrice > 0 &&
        this.maxPrice != null &&
        this.maxPrice > 0
      ) {
        param.minPrice = this.minPrice;
        param.maxPrice = this.maxPrice;
      }
      console.log("查询的参数为：", param);
      this.$axios
        .get("http://localhost:8089/food/groupFood", { params: param })
        .then((r) => {
          this.foodData = r;
          this.foodDatac = []; //用于copy数组
          this.numArray = [];
          this.foodData.forEach((f) => {
            f.count = 0;
            this.foodDatac.push(f);
            this.numArray.push(0);
          });
          console.log("组合所有食物：", this.foodDatac);
          this.$message.success("查询成功！");
        });
    },

    //切换食物类型
    chooseFoodType(ftId) {
      this.foodDatac = [];
      this.foodData.forEach((f) => {
        if (f.foodtypeByFtId.ftId == ftId) {
          this.foodDatac.push(f);
        }
      });
    },
    //加入购物车
    addCart(food, count) {
      if (count < 1) {
        this.$message.warning("请先选择数量！");
        return;
      }
      let isExist = false;
      //加入购物车的食物如果存在则改变 isExist为true
      if (this.chooseFood.length > 0) {
        for (let i = 0; i < this.chooseFood.length; i++) {
          if (food.fId == this.chooseFood[i].fId) {
            isExist = true;
            break;
          }
        }
      }
      if (isExist) {
        //加入购物车的食物存在购物车中则累加数量
        this.chooseFood.forEach((c) => {
          if (c.fId == food.fId) {
            c.count += count;
          }
        });
      } else {
        //加入购物车的食物不存在购物车中则加这个食物
        food.count = count;
        this.chooseFood.push(food);
      }
      //购物车食物总数量 重新赋值
      this.chooseTotalCount = 0;
      this.chooseFood.forEach((c) => {
        this.chooseTotalCount += c.count;
      });
      //调用查询/更新某一个商品加入购物的数量
      this.existCount(food.fId);
      this.$message.success("加入菜单成功！");
    },
    //当某一个商品存在购物中，查询它加入的数量
    existCount(fId){
        let count=0;
        if(this.chooseFood.length){
            for(let i=0;i<this.chooseFood.length;i++){
                if(this.chooseFood[i].fId==fId){
                    count=this.chooseFood[i].count;
                }
            }
        }
        console.log("当前操作的食物在购物车中的数量为：",count);
        return count;
    },
    //进入购物车
    goCart() {
      if (this.chooseFood.length < 1) {
        this.$message.warning("请先添加食物至购物车！");
        return;
      }
      if (this.oldmanId == "" || this.oldmanId < 1) {
        this.$message.warning("请先选择点餐老人！");
        return;
      }
      //给购物车中的每一样食物计算小计
      this.chooseFood.forEach((c) => {
        c.priceTotal =  c.count * c.fPrice;
      });
      
      this.chooseFood.push({});
      this.chooseFood.pop();
      this.cartDialog = true;
    },
    //计算购物车中的总价
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = "合计";
          return;
        }else if(index==2){
            sums[index]="总数量：";
        }else if(index==3){
            sums[index]="总价格：";
        }
        const values = data.map((item) => Number(item[column.property]));
        if (!values.every((value) => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
                if(index==1){
                    return;
                }
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);
          if(index===3){
              sums[index] += " 元";
          }
          
        } else {
            sums[index] = '';
        }
      });

      return sums;
    },
    //删除购物车中的食物
    deleteFood(food) {
      this.chooseFood.splice(this.chooseFood.indexOf(food), 1);
      //购物车食物总数量 重新赋值
      this.chooseTotalCount = 0;
      this.chooseFood.forEach((c) => {
        this.chooseTotalCount += c.count;
      });
    },
    //提交购物车中的食物  新增
    submitFood() {
      //判断购物车中有没有食物
      if (this.chooseFood.length < 1) {
        this.$message.warning("请先选择食物！");
        this.cartDialog = false;
        return;
      }
      //判断有没有选中餐次
      if (this.checkedMelas == "") {
        this.$message.warning("请先选择餐次！");
        return;
      }

      let priceTotal = 0;
      //给点餐老人赋值
      this.foodorder.oldmanByOmId = { omId: this.oldmanId };
      this.chooseFood.forEach((c) => {
        //点餐详情赋值
        let foodorderdetail = {};
        foodorderdetail.food = c;
        foodorderdetail.fodNumber = c.count;
        foodorderdetail.fodTotal = c.priceTotal;
        this.foodorder.foodorderdetails.push(foodorderdetail);

        //点餐总价格叠加
        priceTotal += c.priceTotal;
      });
      //点餐基础属性赋值
      this.foodorder.foMeals = this.checkedMelas;
      this.foodorder.foTime = this.$Dateformat(
        new Date(),
        "yyyy-MM-dd HH:mm:ss"
      );
      this.foodorder.foPrice = priceTotal;
      this.foodorder.foState = 1;
      console.log("即将点餐的数据是：", this.foodorder);
      this.$axios
        .post("http://localhost:8089/foodorder/insert", this.foodorder)
        .then((r) => {
          if (r > 0) {
            this.oldmanId = "";
            this.checkedMelas = "";
            this.chooseFood = [];
            this.chooseTotalCount = 0;
            this.$message.success("点餐成功，请及时送餐！");
            this.cartDialog = false;
          } else {
            this.$message.warning("点餐失败！");
          }
        });
    },
  },
  created() {
    this.loadFoodData();
    this.loadData();
  },
  computed: {
    chooseCount() {
      let count = 0;
      this.chooseFood.forEach((f) => {
        count += f.count;
      });
      return count;
    },
  },
  watch: {},
};
</script>

<style scoped>
/deep/.el-table th > .cell,/deep/.el-table .cell {
  text-align: center;
}
/deep/.el-table .cell .el-button{
    padding:12px 0 0 0;
}
.counts /deep/ .el-input__inner {
  padding: 0 5px;
}
.ulstyle {
  width: 100%;
  list-style: none;
}
.ulstyle li {
  width: 100%;
  text-align: center;
  background: #a8ccdc;
  cursor: pointer;
  color: ghostwhite;
  padding: 2px;
  margin: 2px 0;
}
.ulstyle li:hover {
  color: rgb(255, 208, 75);
}
.maxDiv {
  overflow: hidden;
  overflow-y: auto;
  -webkit-overflow-scrolling: touch;
  background: #dcdcdc36;
  height: 100%;
  padding: 0 10px;
}
.headForm .el-form-item {
  margin-bottom: 5px;
}
.el-aside,
.el-main {
  padding: 10px;
  height: 430px;
  background: #fff;
  box-shadow: 0px 0px 5px 2px gainsboro;
}
/*滚动条样式*/
.scrollbar .el-scrollbar__wrap {
  overflow: scroll;
  width: 110%;
  height: 100%;
  overflow-x: hidden !important;
}
.cards {
  width: 170px;
  height: 290px;
  float: left;
  margin: 0 5px 5px 0;
  box-shadow: 0px 0px 5px 5px gainsboro;
}
.bottom {
  line-height: 12px;
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
</style>