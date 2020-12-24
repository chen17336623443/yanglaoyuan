<template>
  <div class="settlement">
    <div>
      <el-row style="background: white;border-radius: 5px;margin: 10px;padding: 10px">
        <el-col style="font-family: 微软雅黑;">
          <el-tag type="success" style="font-size: 12px;">退住结算与登记</el-tag>
        </el-col>
      </el-row>
    </div>
    <div class="outnei">
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px">
        <el-col :span="5">
          <span style="font-size: 14px">老人姓名：</span>
          <el-input size="small" style="width: 60%" v-model="selectsettlement.tomname" clearable placeholder="请输入姓名"></el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="success" round size="small" @click="selectByPage">查询</el-button>
          <el-button type="info" round size="small" style="margin-left: 10px" @click="insertSettlementShow">退住结算登记</el-button>
        </el-col>
      </el-row>
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px">
        <el-table
          :data="settlementPage"
          border
          style="width: 100%;border-radius: 5px">
          <el-table-column
            prop="seId"
            fixed
            width="100"
            label="编号">
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomName"
            width="120"
            label="老人姓名">
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomSex"
            width="100"
            label="性别">
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomCard"
            width="150"
            label="身份证号">
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomAddress"
            width="150"
            label="地址">
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomCheckIntime"
            width="150"
            label="入院时间">
          </el-table-column>
          <el-table-column
            prop="seTime"
            width="150"
            label="结算时间">
          </el-table-column>
          <el-table-column
            width="100"
            prop="staybacksBySeId[0].stForm"
            label="退住形式">
          </el-table-column>
          <el-table-column
            width="150"
            prop="staybacksBySeId[0].stReason"
            label="出院原因">
          </el-table-column>
          <el-table-column
            width="100"
            fixed="right"
            prop="userByUid.uname"
            label="登记人">
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="current"
          :page-sizes="[2,4, 6]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          style="margin-top: 10px">
        </el-pagination>
      </el-row>
    </div>
    <div>
      <el-dialog
        top="30px"
        :visible.sync="InsertDialogVisible"
        width="60%"
        style="text-align: center;font-size: 12px"
        :before-close="handleClose">
        <div style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 20px;border-radius: 10px">
          <fieldset style="border: none;border-top:1px solid gainsboro;margin: 0px auto;width: 60%">
            <legend style="padding: 0px 10px">基本信息</legend>
          </fieldset>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>老人姓名：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-autocomplete
                size="small"
                v-model="state"
                style="width: 80%"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入姓名"
                @select="handleSelect"
              ></el-autocomplete>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>餐饮套餐等级：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-input size="small" v-model="selectoldMan.foodpackge.fpName" style="width: 80%" disabled></el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>护理套餐等级：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-input size="small" v-model="selectoldMan.thenursinglevelByTslId.tslName" style="width: 80%" disabled></el-input>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>出院形式：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-select v-model="insertSettlement.stayback.stform" clearable placeholder="请选择" size="small" style="width: 80%">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>出院原因：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                v-model="insertSettlement.stayback.streason"
                :rows="3"
                placeholder="请输入内容">
              </el-input>
            </el-col>
          </el-row>
          <fieldset style="border: none;border-top:1px solid gainsboro;margin: 0px auto;width: 60%;margin-top: 20px">
            <legend style="padding: 0px 10px">餐饮消费记录</legend>
          </fieldset>
          <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px">
            <el-table
              :data="selectoldMan.foodordersByOmId"
              border
              style="width: 100%;border-radius: 5px">
              <el-table-column
                prop="foId"
                label="编号">
              </el-table-column>
              <el-table-column
                prop="foPrice"
                label="消费金额">
              </el-table-column>
              <el-table-column
                prop="foTime"
                label="时间">
              </el-table-column>
              <el-table-column
                prop="foMeals"
                label="备注">
              </el-table-column>
            </el-table>
          </el-row>
          <fieldset style="border: none;border-top:1px solid gainsboro;margin: 0px auto;width: 60%;margin-top: 20px">
            <legend style="padding: 0px 10px">护理消费记录</legend>
          </fieldset>
          <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px">
            <el-table
              :data="selectoldMan.nursingrecordsByOmId"
              border
              style="width: 100%;border-radius: 5px">
              <el-table-column
                prop="nrId"
                label="编号">
              </el-table-column>
              <el-table-column
                prop="nrMoney"
                label="消费金额">
              </el-table-column>
              <el-table-column
                prop="nrTime"
                label="时间">
              </el-table-column>
              <el-table-column
                prop="nursingByNuId.nuName"
                label="备注">
              </el-table-column>
            </el-table>
          </el-row>
          <fieldset style="border: none;border-top:1px solid gainsboro;margin: 0px auto;width: 60%;margin-top: 20px">
            <legend style="padding: 0px 10px">老人缴费记录</legend>
          </fieldset>
          <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px">
            <el-table
              :data="selectoldMan.costflowsByOmId"
              border
              style="width: 100%;border-radius: 5px">
              <el-table-column
                prop="cosId"
                label="编号">
              </el-table-column>
              <el-table-column
                prop="cosMoney"
                label="缴费金额">
              </el-table-column>
              <el-table-column
                prop="cosDate"
                label="时间">
              </el-table-column>
              <el-table-column
                prop="cosRemarks"
                label="备注">
              </el-table-column>
            </el-table>
          </el-row>
          <el-row style="margin-top: 20px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px;text-align: left;font-size: 13px">
            <span style="margin-right: 40px">餐饮消费总计：<i class="fa fa-cny"></i> {{thenursingsum}}</span>
            <span style="margin-right: 40px">护理消费总计：<i class="fa fa-cny"></i> {{nuringsum}}</span>
            <span style="margin-right: 40px">床位消费总计：<i class="fa fa-cny"></i> {{bedallmoney}}</span>
            <span style="margin-right: 40px">总消费：<i class="fa fa-cny"></i> {{thenursingsum+nuringsum}}</span>
          </el-row>
          <el-row style="margin-top: 20px;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px;text-align: left;font-size: 13px">
            <span style="margin-right: 40px">已交入住费合计：<i class="fa fa-cny"></i> {{homesum}}</span>
            <span style="margin-right: 40px">应交费用：<i class="fa fa-cny"></i> {{jiaoMoney}}</span>
            <span style="margin-right: 40px">应退费用：<i class="fa fa-cny"></i> {{tuiMoney}}</span>
          </el-row>
          <div style="text-align: center;margin-top: 20px">
            <span slot="footer" class="dialog-footer">
              <el-button type="info" round size="small" @click="insertSettlementClose">取 消</el-button>
              <el-button type="success" round size="small" @click="insertSettlementuplode">确 定</el-button>
            </span>
          </div>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Visit",
    data(){
      return{
        uid:'',
        uname:'',
        total:0,
        pageSize:2,
        current:1,
        /*退住分页载体*/
        settlementPage:[],
        /*老人结算时*/
        restaurants: [],
        state: '',
        timeout:  null,
        /*餐饮消费总计*/
        thenursingsum:0,
        /*护理消费总计*/
        nuringsum:0,
        /*已交入住费合计*/
        homesum:0,
        /*总床位费*/
        bedallmoney:0,
        /*应交费用*/
        jiaoMoney:0,
        /*应退费用*/
        tuiMoney:0,
        /*出院*/
        insertSettlement:{
          settlement:{
            sedue:'',
            sewithdrawn:''
          },
          stayback:{
            stform:'',
            streason:'',
          },
          oldmanByOmId:{
            omId:'',
            tomName:''
          },
        },
        /*搜索到的用户*/
        selectoldMan:{
          foodpackge:{},
          thenursinglevelByTslId:{}
        },
        /*搜索参数*/
        selectsettlement:{
          tomname:''
        },
        InsertDialogVisible:false,
        restaurants: [],
        state: '',
        timeout:  null,
        options:[{
          value: '出院',
          label: '出院'
        },{
          value: '转院',
          label: '转院'
        },{
          value: '死亡',
          label: '死亡'
        }],
      }
    },
    methods:{
      /*结算弹窗显示*/
      insertSettlementShow(){
        this.InsertDialogVisible=true;
        this.uname=localStorage.getItem("uname");
        this.uid=localStorage.getItem("uid");
        let mm=[];
        this.$axios.post("oldman/selectNotSettle")
          .then(r=>{
            r.forEach(item=>{
              let ss={"value":item.tomName,"address":item.omId};
              mm.push(ss);
            })
          })
        this.restaurants=mm;
      },
      querySearchAsync(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;
        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          cb(results);
        });
      },
      createStateFilter(queryString) {
        return (state) => {
          return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      handleSelect(item) {
        this.insertSettlement.oldmanByOmId.omId=item.address;
        this.$axios.post("oldman/selectByomid",this.$qs.stringify({
          omid:this.insertSettlement.oldmanByOmId.omId
        }))
        .then(r=>{
          this.selectoldMan=r;
          let date1=r.tomCheckIntime;
          /*总护理费*/
          let m=0;
          r.nursingrecordsByOmId.forEach(item=>{
            m=m+item.nrMoney;
          })
          /*总护理套餐费*/
          let allHulimoney=this.countsum(date1,r.foodpackge.fpPrice);
          this.nuringsum=allHulimoney+m;
          /*总充值费*/
          let m1=0;
          r.costflowsByOmId.forEach(item=>{
            m1=m1+item.cosMoney;
          })
          this.homesum=m1;
          /*总餐饮费*/
          let m2=0;
          r.foodordersByOmId.forEach(item=>{
            m2=m2+item.foPrice;
          })
          /*总套餐费*/
          let allmoney=this.countsum(date1,r.thenursinglevelByTslId.tslMoney);
          this.thenursingsum=allmoney+m2;

          /*查询床价格*/
          this.$axios.post("oldman/selecyBybid",this.$qs.stringify({
            bid:r.beds[0].bid
          }))
          .then(r=>{
            this.bedallmoney=this.countsum(date1,r.bedtypeByByid.bymoney);
          });

          let suchall=this.thenursingsum+this.bedallmoney+this.nuringsum;
          let cha=this.homesum-suchall;
          if (cha<0){
            this.jiaoMoney=cha*-1;
            this.tuiMoney=0;
          }else {
            this.jiaoMoney=0;
            this.tuiMoney=cha;
          }
          this.insertSettlement.settlement.sedue=this.jiaoMoney;
          this.insertSettlement.settlement.sewithdrawn=this.tuiMoney;
          this.insertSettlement.oldmanByOmId.omId=r.omId;
          console.log(r,"所有的数据");
        })
      },
      insertSettlementuplode(){
        if (this.insertSettlement.stayback.stform==null || this.insertSettlement.stayback.stform==''){
          this.$message({
            type: 'warning',
            message: '出院类型不能为空！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else{
          this.$axios.post("settlement/insertSettlement",this.$qs.stringify({
            sedue:this.insertSettlement.settlement.sedue,
            sewithdrawn:this.insertSettlement.settlement.sewithdrawn,
            streason:this.insertSettlement.stayback.streason,
            stform:this.insertSettlement.stayback.stform,
            omId:this.insertSettlement.oldmanByOmId.omId,
            uid:this.uid
          }))
          .then(r=>{
              this.$message({
                type: 'success',
                message: '出院成功！！！',
                duration: 1000,
                showClose: true,
              })
              this.insertSettlement={
                settlement:{
                  sedue:'',
                  sewithdrawn:''
                },
                stayback:{
                  stform:'',
                  streason:'',
                },
                oldmanByOmId:{
                  omId:'',
                  tomName:''
                },
              };
              this.selectoldMan={
                foodpackge:{},
                thenursinglevelByTslId:{}
              },
              this.state='';
              this.thenursingsum=0;
              this.nuringsum=0;
              this.homesum=0;
              this.bedallmoney=0;
              this.jiaoMoney=0;
              this.tuiMoney=0;
              this.InsertDialogVisible=false;
              this.selectByPage();
            })
        }
      },
      insertSettlementClose(){
        this.insertSettlement={
          settlement:{
            sedue:'',
            sewithdrawn:''
          },
          stayback:{
            stform:'',
            streason:'',
          },
          oldmanByOmId:{
            omId:'',
            tomName:''
          },
        };
        this.state='';
        this.thenursingsum=0;
        this.nuringsum=0;
        this.homesum=0;
        this.bedallmoney=0;
        this.jiaoMoney=0;
        this.tuiMoney=0;
        this.selectoldMan={
          foodpackge:{},
          thenursinglevelByTslId:{}
        },
        this.InsertDialogVisible=false;
      },
      /*计算几个月价钱*/
      countsum(date,money){
        let allTaoCanmoney=0;
        if(parseInt(date.split(" ")[0].split("-")[0])<=new Date().getFullYear()&&new Date().getFullYear()-parseInt(date.split(" ")[0].split("-")[0])==0){
          let m=new Date().getMonth()-parseInt(date.split(" ")[0].split("-")[1]);
          if (m==-1){
            allTaoCanmoney=money;
          }else{
            allTaoCanmoney=(m+1)*money;
          }
          console.log("消费1",allTaoCanmoney);
        }else if (parseInt(date.split(" ")[0].split("-")[0])<=new Date().getFullYear()&&new Date().getFullYear()-parseInt(date.split(" ")[0].split("-")[0])==1){
          let m=12-parseInt(date.split(" ")[0].split("-")[1])+new Date().getMonth();
          allTaoCanmoney=(m+1)*money;
          console.log("消费2",allTaoCanmoney);
        }else if(parseInt(date.split(" ")[0].split("-")[0])<=new Date().getFullYear()&&new Date().getFullYear()-parseInt(date.split(" ")[0].split("-")[0])>1&&new Date().getMonth()<=parseInt(date.split(" ")[0].split("-")[1])){
          let m=12-parseInt(date.split(" ")[0].split("-")[1])+new Date().getMonth()+12*(new Date().getFullYear()-parseInt(date.split(" ")[0].split("-")[0])-1);
          allTaoCanmoney=(m+1)*money;
          console.log("消费3",allTaoCanmoney);
        }else{
          let m=12-parseInt(date.split(" ")[0].split("-")[1])+new Date().getMonth()+12*(new Date().getFullYear()-parseInt(date.split(" ")[0].split("-")[0]));
          allTaoCanmoney=(m+1)*money;
          console.log("月份",m);
          console.log("消费4",allTaoCanmoney);
        }
        return allTaoCanmoney;
      },
      /*分页模糊查询*/
      selectByPage(){
        this.$axios.post("settlement/selectByPage",this.$qs.stringify({
          no:this.current,
          size:this.pageSize,
          tomname:this.selectsettlement.tomname
        }))
        .then(r=>{
          this.settlementPage=r.list;
          this.total=r.total;
          console.log(r,"查询到的数据");
        })
      },
      /*分页查询底部*/
      handleCurrentChange(pagerindex){
        //参数是当前页码
        this.current = pagerindex;
        this.selectByPage();
      },
      /* pageSize 改变时会触发*/
      handleSizeChange(pagesize){
        this.pageSize=pagesize;
        this.selectByPage();
      },
      /*关闭退院弹窗*/
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
    },
    created() {
      this.selectByPage();
    }
  }
</script>

<style>
  .settlement{
    overflow: hidden;
    overflow-y: auto;
    -webkit-overflow-scrolling: touch;
    background: #dcdcdc36;
    height: 100%;
  }
  .settlement .outnei{
    background: white;
    margin: 10px;
    padding: 10px;
    border-radius: 5px;
    height: 82%;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
  .settlement .cell{
    text-align: center;
    font-size: 12px;
  }
  .settlement textarea {
    font-family: Arial, Helvetica, sans-serif;
    resize: none;
  }
  .settlement .el-dialog__body{
    padding:0px 20px 30px
  }
  .settlement .el-dialog{
    border-radius: 10px;
  }
  .settlement .el-dialog__headerbtn{
    top: 10px;
  }
</style>

