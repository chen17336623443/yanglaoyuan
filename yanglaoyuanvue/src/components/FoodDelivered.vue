<template>
  <div class="maxDiv">
    <el-form
      :inline="true"
      class="demo-form-inline"
      style="margin-top:10px;"
    >
      <el-form-item label="送餐区域：">
        <el-input
          v-model="fdAddress"
          clearable
          style="width:180px;"
        />
      </el-form-item>
      <el-form-item label="送餐时间：">
        <el-date-picker
          v-model="fdTime"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item><el-button @click="isGroup=true,loadData()">查询</el-button></el-form-item>
    </el-form>
    <el-table :data="fooddeliData" style="width:100%" border>
        <el-table-column type="expand">
            <template slot-scope="scope">
                <el-form label-position="left" inline class="demo-table-expand tss" label-width="100px">
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="点餐详情：">
                                <el-tag v-for="f in scope.row.foodorderByFoId.foodorderdetails" :key="f.fodId" style="margin-right:20px;">
                                    {{f.food.fName}}({{f.fodNumber}})/{{f.fodTotal}}元
                                </el-tag>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="合计：">
                                <span>总数量：{{totalCount(scope.row.foodorderByFoId)}}</span>
                                <span style="margin-left:50px;">总金额：{{scope.row.foodorderByFoId.foPrice}}元</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </template>
        </el-table-column>
        <el-table-column prop="fdId" label="ID"></el-table-column>
        <el-table-column label="点餐客户">
            <template slot-scope="scope">
                {{scope.row.foodorderByFoId.oldmanByOmId.tomName}}
            </template>
        </el-table-column>
        <el-table-column label="点餐类型">
            <template slot-scope="scope">
                {{scope.row.fdType==1?'自主点餐':'日常配餐'}}
            </template>
        </el-table-column>
        <el-table-column prop="fdTime" sortable label="送餐时间"></el-table-column>
        <el-table-column prop="fdAddress" label="送餐地址"></el-table-column>
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
      pageSize: 5,
      current: 1,
      total: 0,
      //送餐数据
      fooddeliData: [],
      //送餐地址
      fdAddress:'',
      //送餐时间
      fdTime:[],
      //是否组合查询
      isGroup:false,
      //连续组合查询次数
      groupCount:0,
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
    loadData() {
      let url=this.isGroup?'http://localhost:8089/fooddeli/groupQuery':'http://localhost:8089/fooddeli/all';
      let param={pageNo:this.current,pageSize:this.pageSize};
      if(this.isGroup){
          if(this.fdAddress.length<1 && this.fdTime.length<1){
            this.$message.warning("请至少输入一个条件！(已为您查询所有)");
            this.current=1;
            this.isGroup=false;
            this.loadData();
            return;
        }
        //组合查询次数+1
        this.groupCount++;
        this.current=(this.groupCount>1)?this.current:1;
        if(this.fdAddress.length>0)param.fdAddress=this.fdAddress;
        if(this.fdTime.length>1){
            param.startTime=this.$Dateformat(this.fdTime[0],'yyyy-MM-dd HH:mm:ss');
            param.entTime=this.$Dateformat(this.fdTime[1],'yyyy-MM-dd HH:mm:ss');
        }
      }
      console.log("即将查询的参数：",param);
      //查询送餐记录
      this.$axios
        .get(url,{params:param})
        .then((r) => {
            console.log("送餐数据：",r);
          this.fooddeliData = r.list;
          this.total = r.total;
        });
    },
    //合计数量
    totalCount(foodorder){
        let count=0;
        foodorder.foodorderdetails.forEach(f=>{
            count+=f.fodNumber;
        });
        return count;
    },
    
  },
  created() {
    this.loadData();
  },
};
</script>

<style scoped>
.maxDiv{
  overflow: hidden;
  overflow-y: auto;
  -webkit-overflow-scrolling: touch;
  background: #dcdcdc36;
  height: 100%;
  padding: 0 10px;
  
}
</style>