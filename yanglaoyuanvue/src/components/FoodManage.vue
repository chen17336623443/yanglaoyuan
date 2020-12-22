<template>
    <div class="maxDiv">
        <div class="headDiv">
            <el-row>
                <el-col :span="24">
                    <el-col :span="20">
                        <el-form :inline="true" class="demo-form-inline">
                            <el-form-item label="食物类别">
                                <el-select v-model="foodType" placeholder="请选择">
                                    <el-option v-for="f in foodtypeData" :key="f.ftId" :label="f.ftName" :value="f.ftId"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="食物名称">
                                <el-input v-model="foodName" placeholder="请输入食物名称"/>
                            </el-form-item>
                            <el-form-item label="食物描述">
                                <el-input v-model="foodRemark" placeholder="请输入食物描述"/>
                            </el-form-item>
                        </el-form>
                    </el-col>
                    <el-col :span="4">
                        <el-button>新增</el-button>
                        <el-button>查询</el-button>
                    </el-col>
                </el-col>
            </el-row>
            
        </div>
        <div class="bodyDiv">
            <el-card :body-style="{ padding: '0px' }" v-for="f in foodData" :key="f.fId" class="cards">               
                <img :src="'../../static/imgs/'+f.fImg" class="image">
                <div style="padding: 14px;">
                    <p style="text-align:center;">
                        <el-popover
                            placement="top"
                            width="200"
                            trigger="hover"
                            :content="f.fRemark">
                            <el-tag style="cursor:pointer;" slot="reference">{{f.fName}}</el-tag>
                        </el-popover>
                        <el-tag type="warning">{{f.fPrice}}元</el-tag>                       
                    </p>
                    <div class="bottom clearfix">                       
                        <el-button type="text" @click="readyUpdate(f)" class="button" icon="el-icon-edit" style="color: #E6A23C;font-size:12px;">修改</el-button>
                        <el-button type="text" class="button" icon="el-icon-delete" style="color: #F56C6C;font-size:12px;">删除</el-button>
                    </div>
                </div>
            </el-card>
        </div>
        <el-dialog :visible.sync="foodDialog" width="50%">
            <span slot="title">
                <i :class="isAdd?'el-icon-plus':'el-icon-edit'"></i>
                {{isAdd?'食物新增':'食物修改'}}
            </span>
            <el-form :model="food" label-width="80px">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="名称：">
                            <el-input v-model="food.fName"/>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="类别：">
                            <el-select v-model="food.foodtypeByFtId.ftId">
                                <el-option v-for="f in foodtypeData" :key="f.ftId" :label="f.ftName" :value="f.ftId"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="价格：">
                            <el-input v-model.number="food.fPrice"/>
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

                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="描述：">
                            <el-input type="textarea" v-model="food.fRemark"/>
                        </el-form-item>
                        
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="foodDialog = false">取 消</el-button>
                <el-button type="primary" @click="foodDialog = false">确 定</el-button>
            </div>
        </el-dialog>
    </div>
  
</template>

<script>
export default {
    data(){
        return{
            current:1,
            pageSize:5,
            total:0,
            foodData:[],
            foodtypeData:[],
            foodType:0,
            foodName:'',
            foodRemark:'',
            foodDialog:false,
            isAdd:true,
            food:{
                foodtypeByFtId:{}
            },
            ladu:[
                {id:1,value:'不辣'},{id:2,value:'微辣'},{id:3,value:'中辣'},{id:4,value:'特辣'}
            ],
            kouwei:[
                {id:1,value:'辣味'},{id:2,value:'甜味'},{id:3,value:'淡味'},{id:4,value:'咸味'},{id:5,value:'酸味'},
                {id:6,value:'苦味'},{id:7,value:'涩味'},{id:8,value:'其味无穷'}
            ]
        }
    },methods:{
        loadData(){
            let that=this;
            this.$axios.get("http://localhost:8089/food/all?pageNo="+this.current+"&pageSize="+this.pageSize)
            .then(r=>{
                console.log(r);
                that.foodData=r.list;
                that.total=r.total;
            });
            this.$axios.get("http://localhost:8089/food/alltype")
            .then(r=>{
                console.log(r);
                that.foodtypeData=r;
            })
            
        },
        readyUpdate(food){
            this.food={...food};
            this.isAdd=false;
            this.foodDialog=true;
        }
    },created(){
        this.loadData();
    }
}
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
.headDiv{
    width: 98.2%;
    height: 7%;
    border-radius: 10px;
    background: #fff;
    box-shadow: 0px 0px 5px 5px gainsboro;
    margin-top: 10px;
    padding: 10px;
}
.bodyDiv{
    width: 98.2%;
    height: 81%;
    border-radius: 10px;
    background: #fff;
    box-shadow: 0px 0px 5px 5px gainsboro;
    margin-top: 10px;
    padding: 10px;
}
.headDiv .el-form-item{
    margin-bottom:10px;
}
.cards{
    width:170px;
    height: 260px;
    float: left;
    margin: 0 10px 10px 0;
    box-shadow: 0px 0px 5px 5px gainsboro;
}
.bottom {
    margin-top: 13px;
    line-height: 12px;
    text-align: right;
  }
.bottom .el-button+.el-button{
    margin-left:0;
}
  .button {
    padding: 0;
  }

  .image {
    width: 170px;
    height:170px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>