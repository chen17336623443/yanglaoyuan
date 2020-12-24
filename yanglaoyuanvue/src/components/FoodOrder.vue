<template>
  <div class="maxDiv">
      <div style="background:#fff;box-shadow: 0px 0px 5px 5px gainsboro;padding:3px 10px;margin-top:10px;">
          <el-form style="width:100%;margin-top:10px;" class="headForm">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="客户：">
                        <el-select v-model="oldmanId" clearable placeholder="请选择客户">
                            <el-option v-for="o in oldmanData" :key="o.omId" :label="o.tomName" :value="o.omId"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-badge :value="chooseTotalCount" class="item">
                        <el-button @click="goCart">已点菜单</el-button>
                    </el-badge>                    
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    <el-form-item label="辣度：">
                        <el-select v-model="ladu" clearable>
                            <el-option v-for="l in ladus" :key="l.id" :label="l.value" :value="l.value"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="口味：">
                        <el-select v-model="kouwei" clearable>
                            <el-option v-for="l in kouweis" :key="l.id" :label="l.value" :value="l.value"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="价格：">
                        <el-input v-model="minPrice" style="width:100px"/>-<el-input v-model="maxPrice" style="width:100px"/>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="16">
                    <el-form-item label="禁忌人群：">
                        <el-checkbox-group v-model="taboocrowds">
                            <el-checkbox v-for="t in taboData" :label="t.tcId" :key="t.tcId">{{t.tcName}}</el-checkbox>
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
                    <li v-for="f in foodType" :key="f.ftId" @click="chooseFoodType(f.ftId)">{{f.ftName}}</li>
                </ul>
            </el-aside>
            <el-main>
                <div style="width: 100%;height: 410px;" class="scrollbar">
                    <el-scrollbar style="height: 100%;">
                        <el-card
                            :body-style="{ padding: '0px' }"
                            v-for="f in foodDatac"
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
                                    <el-button type="text" style="padding:3px;" @click="miusNumber(f)">-</el-button>
                                    <el-input size="mini" v-model.number="f.count" style="width:30px;" class="counts"/>
                                    <el-button type="text" style="padding:3px;" @click="addNumber(f)">+</el-button>
                                    <el-button type="text" icon="el-icon-shopping-cart-2" style="color: #E6A23C;font-size:12px;" @click="addCart(f)">加入</el-button>
                                </div>
                            </div>
                        </el-card>
                    </el-scrollbar>
                </div>
            </el-main>
        </el-container>
      </div>
      <el-dialog title="已选菜单" :visible.sync="cartDialog" width="60%">
          <el-row>
              <el-col :span="24">
                  餐次：
                  <el-select v-model="checkedMelas" clearable placeholder="请选择">
                     <el-option v-for="m in melas" :key="m.id" :label="m.value" :value="m.value"></el-option>
                  </el-select>
              </el-col>
          </el-row>
          <el-table :data="chooseFood" border>
              <el-table-column prop="fName" label="食品名称"></el-table-column>
              <el-table-column prop="fPrice" label="食品价格"></el-table-column>
              <el-table-column prop="count" label="选购数量"></el-table-column>
              <el-table-column prop="priceTotal" label="小计"></el-table-column>
              <el-table-column label="操作">
                  <template slot-scope="scope">
                      <el-button type="text" @click="minusFood(scope.row)" style="padding:3px;">-</el-button>
                      <el-button type="text" @click="addFood(scope.row)" style="padding:3px;">+</el-button>
                      <el-popconfirm
                        confirm-button-text='确定'
                        cancel-button-text='取消'
                        icon="el-icon-info"
                        icon-color="red"
                        :title="'确定要将'+scope.row.fName+'移出吗？'"
                        @onConfirm="deleteFood(scope.row)"
                        >
                        <el-button slot="reference" icon="el-icon-delete" type="text" style="color: #F56C6C;">删除</el-button>
                      </el-popconfirm>
                      
                  </template>
              </el-table-column>
          </el-table>
          <el-row>
              <el-col :span="24" style="text-align:center">
                  
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
      foodDatac:[],
      //所有老人
      oldmanData: [],
      //禁忌人群
      taboData: [],
      //老人id
      oldmanId:'',
      ladu:'',
      kouwei:'',
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
      minPrice:'',
      //最高价格
      maxPrice:'',
      //已选择的禁忌人群
      taboocrowds:[],
      //所有食物类型
      foodType:[],
      //已选择至购物车中的数据
      chooseFood:[],
      chooseTotalCount:0,
      //购物车对话框
      cartDialog:false,
      //餐次
      melas:[{id:1,value:'早餐'},{id:2,value:'午餐'},{id:3,value:'晚餐'}],
      //所选餐次
      checkedMelas:'',
      //点餐对象
      foodorder:{
          //点餐老人
          oldmanByOmId:{},
          //点餐详情
          foodorderdetails:[]
      }
    };
  },
  methods: {
    loadFoodData() {
      this.$axios
        .get(
          "http://localhost:8089/food/allNotPage"
        )
        .then((r) => {
          this.foodData =r;
          this.foodDatac=[];
          this.foodData.forEach(f=>{
              f.count=1;
              f.priceTotal=0;
              this.foodDatac.push(f);
          })
          console.log("所有食物：",this.foodDatac);
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
      this.$axios.get("http://localhost:8089/food/alltype")
      .then(r=>{
          this.foodType=r;
      })
    },
    //组合查询
    groupQuery(){
        if(this.ladu.length<1 && this.kouwei.length<1 && this.taboocrowds.length<1 && 
            (this.minPrice=="" && this.maxPrice=="")){
            this.$message.warning("请至少输入一个条件！(已为您查询所有)");
            this.loadFoodData();
            return;
        }
        let param={};
        if(this.ladu.length>0)param.fPiquancy=this.ladu;
        if(this.kouwei.length>0)param.fTaste=this.kouwei;
        if(this.taboocrowds.length>0)param.list=this.taboocrowds.join(",");
        if(this.minPrice!=null && this.minPrice>0 && this.maxPrice!=null && this.maxPrice>0){
            param.minPrice=this.minPrice;
            param.maxPrice=this.maxPrice;
        }
        console.log("查询的参数为：",param);
        this.$axios.get("http://localhost:8089/food/groupFood",{params:param})
        .then(r=>{
            this.foodData =r;
            this.foodDatac=[];
            this.foodData.forEach(f=>{
                f.count=1;
                this.foodDatac.push(f);
            })
            console.log("组合所有食物：",this.foodDatac);
        })
    },
    //减数量
    miusNumber(food){
        if(food.count==1){
            this.$message.warning("最小数量为1！");
            return;
        }
        food.count--;
        this.$set(this.chooseFood,this.chooseFood[this.chooseFood.indexOf(food)],food);
    },
    //加数量
    addNumber(food){
        food.count++;
        this.$set(this.chooseFood,this.chooseFood[this.chooseFood.indexOf(food)],food);
    },
    //切换食物类型
    chooseFoodType(ftId){
        this.foodDatac=[];
        this.foodData.forEach(f=>{
            if(f.foodtypeByFtId.ftId==ftId){
                this.foodDatac.push(f);
            }
        })
    },
    //加入购物车
    addCart(food){
        if(this.chooseFood.indexOf(food)==-1){
            this.chooseFood.push(food);
        }else{
            this.chooseFood.forEach(c=>{
                if(c.fId==food.fId){
                    c.count++;
                }
            })
        }
        
    },
    //进入购物车
    goCart(){
        if(this.chooseFood.length<1){
            this.$message.warning("请先添加食物至购物车！");
            return;
        }
        if(this.oldmanId=="" || this.oldmanId<1){
            this.$message.warning("请先选择点餐老人！");
            return;
        }
        this.chooseFood.forEach(c=>{
            c.priceTotal=c.fPrice*c.count;
        });
        this.cartDialog=true;
    },
    //购物车中减数量
    minusFood(food){
        if(food.count==1){
            this.$message.warning("至少有一个数量！");
            return;
        }
        this.chooseFood.forEach(c=>{
            if(c.fId==food.fId){
                console.log("开始减数量");
                c.count--;
                c.priceTotal=c.count*c.fPrice;
                this.$set(food,'count',c.count);
                this.$set(food,'priceTotal',c.priceTotal);
            }
        })
        
    },
    //购物车中加数量
    addFood(food){
        this.chooseFood.forEach(c=>{
            if(c.fId==food.fId){
                console.log("开始加数量");
                c.count++;
                c.priceTotal=c.count*c.fPrice;
                this.$set(food,'count',c.count);
                this.$set(food,'priceTotal',c.priceTotal);
            }
        })
    },
    //删除购物车中的食物
    deleteFood(food){
        console.log("进入删除");
        this.chooseFood.splice(this.chooseFood.indexOf(food),1);
        if(this.chooseFood.indexOf(food)>-1){
            console.log("开始删除！");
        }
    },
    //提交购物车中的食物  新增
    submitFood(){
        if(this.checkedMelas==""){
            this.$message.warning("请先选择餐次！");
            return;
        }
        let priceTotal=0;
        //给点餐老人赋值
        this.foodorder.oldmanByOmId={omId:this.oldmanId};
        this.chooseFood.forEach(c=>{
            //点餐详情赋值
            let foodorderdetail={};
            foodorderdetail.food=c;
            foodorderdetail.fodNumber=c.count;
            foodorderdetail.fodTotal=c.priceTotal;
            this.foodorder.foodorderdetails.push(foodorderdetail);

            //点餐总价格叠加
            priceTotal+=c.priceTotal;
        });
        //点餐基础属性赋值
        this.foodorder.foMeals=this.checkedMelas;
        this.foodorder.foTime=this.$Dateformat(new Date(),'yyyy-MM-dd HH:mm:ss');
        this.foodorder.foPrice=priceTotal;
        this.foodorder.foState=1;
        console.log("即将点餐的数据是：",this.foodorder);
        this.$axios.post("http://localhost:8089/foodorder/insert",this.foodorder)
        .then(r=>{
            if(r>0){
                this.oldmanId='';
                this.checkedMelas='';
                this.chooseFood=[];
                this.$message.success("点餐成功，请及时送餐！");
                this.cartDialog=false;
            }else{
                this.$message.warning("点餐失败！");
            }
        })
    }
  },
  created() {
    this.loadFoodData();
    this.loadData();
  },computed:{
      chooseCount(){
          let count=0;
          this.foodDatac.forEach(f=>{
              count+=f.count;
          })
          return count;
      }
  }

};
</script>

<style scoped>
.counts /deep/ .el-input__inner{
    padding:0 5px;
}
.ulstyle{
    width: 100%;
    list-style: none;
}
.ulstyle li{
    width: 100%;
    text-align: center;
    background: #A8CCDC;
    cursor: pointer;
    color:ghostwhite;
    padding: 2px;
    margin: 2px 0;
}
.ulstyle li:hover{
    color:rgb(255, 208, 75);
}
.maxDiv {
 
  overflow: hidden;
  overflow-y: auto;
  -webkit-overflow-scrolling: touch;
  background: #dcdcdc36;
  height: 100%;
  padding: 0 10px;
}
.headForm .el-form-item{
    margin-bottom: 5px;
}
.el-aside,.el-main{
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
  height: 255px;
  float: left;
  margin: 0 8px 8px 0;
  box-shadow: 0px 0px 5px 5px gainsboro;
}
.bottom {
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
</style>