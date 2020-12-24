<template>
  <div class="maxDiv">
    <el-form
      :inline="true"
      class="demo-form-inline"
      style="margin-top:10px;"
    >
      <el-form-item label="客户姓名：">
        <el-select v-model="oldmanId" placeholder="请选择" clearable style="width:180px;">
            <el-option v-for="o in oldmanData" :key="o.omId" :label="o.tomName" :value="o.omId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="餐次：">
        <el-select
          v-model="foMeals"
          placeholder="请选择餐次"
          clearable
          style="width:180px;"
        >
          <el-option
            label="早餐"
            value="早餐"
          ></el-option>
          <el-option
            label="午餐"
            value="午餐"
          ></el-option>
          <el-option
            label="晚餐"
            value="晚餐"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="点餐时间：">
        <el-date-picker
          v-model="foTime"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="isGroup=true,loadData()"
        >查询</el-button>
        <el-button @click="toFoodMenu">点餐</el-button>
      </el-form-item>
      
    </el-form>
    <el-table :data="foodorderData" border style="width:100%;text-align:center;">
        <el-table-column prop="foId" label="ID" min-width="8%"></el-table-column>
        <el-table-column label="客户姓名" min-width="10%">
            <template slot-scope="scope">
                {{scope.row.oldmanByOmId.tomName}}
            </template>
        </el-table-column>
        <el-table-column prop="foMeals" label="餐次" min-width="8%"></el-table-column> 
        <el-table-column prop="foTime" label="下单时间" min-width="15%"></el-table-column> 
        <el-table-column label="点餐详情" min-width="13%">
            <template slot-scope="scope">
                <span v-for="d in scope.row.foodorderdetails" :key="d.fodId">
                    {{d.food.fName}}({{d.fodNumber}})
                    <el-tooltip content="小计" placement="right">
                        <el-tag type="warning">{{d.fodTotal}}元</el-tag>
                    </el-tooltip>
                    <span v-if="scope.row.foodorderdetails.length>1"><br/></span>
                </span>
            </template>
        </el-table-column>
        <el-table-column prop="foPrice" label="金额" min-width="10%">
            <template slot-scope="scope">
                {{scope.row.foPrice}}元
            </template>
        </el-table-column>
        <el-table-column label="状态" min-width="12%">
            <template slot-scope="scope">
                <el-tag :type="scope.row.foState==1?'warning':'success'">
                    {{scope.row.foState==1?'待送餐':'已送餐'}}
                </el-tag>
            </template>
        </el-table-column>
        <el-table-column label="操作" min-width="10%">
            <template slot-scope="scope">
                <el-button v-if="scope.row.foState==1" type="text" 
                icon="el-icon-truck" style="color: #E6A23C;font-size:12px;"
                @click="beginMeals(scope.row)">送餐</el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="current"
        :page-sizes="[5, 10]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
        //老人id
        oldmanId:'',
        //餐次
        foMeals:'',
        //点餐时间
        foTime:[],
        pageSize:5,
        current:1,
        total:0,
        //点餐数据
        foodorderData:[],
        //点过餐的老人数据
        oldmanData:[],
        isGroup:false,
        groupCount:0

    };
  },
  methods: {
    handleSizeChange(pageSize){
        this.pageSize=pageSize;
        this.loadData();
    },
    handleCurrentChange(current){
        this.current=current;
        this.loadData();
    },
    //组合查询
    groupQuery() {},
    //加载数据  + 组合查询
    loadData(){
        let url=this.isGroup?"http://localhost:8089/foodorder/groupQuery":"http://localhost:8089/foodorder/all";
        let param={pageNo:this.current,pageSize:this.pageSize};
        if(this.isGroup){
            if(this.oldmanId=="" && this.foMeals.length<1 && this.foTime.length>1){
                this.loadData();
                this.isGroup=false;
                this.current=1;
                this.$message.warning("请至少输入一个条件！(已为您查询所有)");
                return;
            }
            this.groupCount++;
            this.current=(this.groupCount>1)?this.current:1;
            if(this.oldmanId!=null && this.oldmanId>0)param.om_id=this.oldmanId;
            if(this.foMeals.length>0)param.fo_meals=this.foMeals;
            if(this.foTime.length>1){
                param.startTime=this.$Dateformat(this.foTime[0],'yyyy-MM-dd HH:mm:ss');
                param.endTime=this.$Dateformat(this.foTime[1],'yyyy-MM-dd HH:mm:ss');
            }
        }
        //加载点餐数据
        this.$axios.get(url,{params:param})
        .then(r=>{
            console.log(r);
            this.foodorderData=r.list;
            this.total=r.total;
        });
        //加载老人数据
        this.$axios.get("http://localhost:8089/foodorder/haveFoid")
        .then(r=>{
            this.oldmanData=r;
        })
    },
    //点餐
    toFoodMenu(){
        this.$router.push("/foodOrder");
    },
    //开始送餐
    beginMeals(foodorder){
        //给点餐对象赋值
        let fdel={};
        fdel.foodorderByFoId=foodorder;
        fdel.fdType=1;
        fdel.fdTime=this.$Dateformat(new Date(),'yyyy-MM-dd HH:mm:ss');
        fdel.fdAddress=foodorder.oldmanByOmId.tomAddress;
        //新增
        this.$axios.post("http://localhost:8089/fooddeli/insert",fdel)
        .then(r=>{
            if(r=>0){
                this.loadData();
                this.$message.success("已开始送餐！");
            }
        })
    }
  },created(){
      this.loadData();
  }
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
/deep/.el-table th > .cell,/deep/.el-table .cell{
    text-align: center;
}
</style>