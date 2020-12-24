<template>
  <div class="outregistration">
    <div>
      <el-row style="background: white;border-radius: 5px;margin: 10px;padding: 10px">
        <el-col style="font-family: 微软雅黑;">
          <el-tag type="success" style="font-size: 12px;">用户登记外出/返回</el-tag>
        </el-col>
      </el-row>
    </div>
    <div class="outnei">
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px">
        <el-col :span="5">
          <span style="font-size: 14px">老人姓名：</span>
          <el-autocomplete
            size="small"
            v-model="state"
            style="width: 60%"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入姓名"
            @select="handleSelect"
          ></el-autocomplete>
        </el-col>
        <el-col :span="5">
          <span style="font-size: 14px">记录类型：</span>
          <el-select v-model="selectOutregistration.outtrue" clearable placeholder="请选择" size="small" style="width: 60%">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="10">
          <span style="font-size: 14px">外出时间：</span>
          <el-date-picker
            size="small"
            style="width: 80%"
            v-model="selectOutregistration.time"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="yyyy-MM-dd HH-mm-ss"
            align="right">
          </el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-button type="success" round size="small" @click="selectByPage">查询</el-button>
          <el-button type="info" round size="small" style="margin-left: 10px" @click="showdialogVisible">办理外出</el-button>
        </el-col>
      </el-row>
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px">
        <el-table
          :data="outregistrationPage"
          border
          style="width: 100%;border-radius: 5px">
          <el-table-column
            fixed
            prop="outId"
            width="100"
            label="编号">
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomName"
            width="100"
            label="老人姓名">
          </el-table-column>
          <el-table-column
            prop="outAccompanyname"
            width="100"
            label="陪同人姓名">
          </el-table-column>
          <el-table-column
            prop="outAccompanyphone"
            width="120"
            label="陪同人电话">
          </el-table-column>
          <el-table-column
            prop="outTime"
            width="150"
            label="外出时间">
          </el-table-column>
          <el-table-column
            prop="outReturnestimate"
            width="150"
            label="预计返回时间">
          </el-table-column>
          <el-table-column
            prop="outReturntime"
            width="150"
            label="实际返回时间">
          </el-table-column>
          <el-table-column
            prop="outOutsum"
            width="100"
            label="外出天数">
            <template slot-scope="x">
              <span v-if="x.row.outtrue==1">
                {{moment(new Date().getTime()).diff(moment(x.row.outTime).getTime(), 'days')}}
              </span>
              <span v-if="x.row.outtrue!=2">
                {{moment(x.row.outReturntime).diff(moment(x.row.outTime), 'days')}}
              </span>
            </template>
          </el-table-column>
          <el-table-column
            prop="outTrue"
            width="100"
            label="是否返回">
            <template slot-scope="x">
              <span v-if="x.row.outTrue==1">
                未返回
              </span>
              <span v-else>
                已返回
              </span>
            </template>
          </el-table-column>
          <el-table-column
            prop="userByUseUid.uname"
            width="100"
            label="办理人">
          </el-table-column>
          <el-table-column
            fixed="right"
            width="150"
            label="操作">
            <template slot-scope="x">
              <el-button v-if="x.row.outTrue==1" type="warning" size="mini" plain @click="updateOutReturnShow(x.row)">延期</el-button>
              <el-button v-if="x.row.outTrue!=1" disabled type="warning" size="mini" plain>延期</el-button>
              <el-button v-if="x.row.outTrue==1" type="success" size="mini" @click="updateOutTrue(x.row)" plain>返回</el-button>
              <el-button v-if="x.row.outTrue!=1" disabled type="success" size="mini" plain >返回</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="current"
          :page-sizes="[2, 4, 6]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          style="margin-top: 10px">
        </el-pagination>
      </el-row>
    </div>
    <div>
      <el-dialog
        top="60px"
        :visible.sync="dialogVisible"
        width="50%"
        style="text-align: center;font-size: 12px"
        :before-close="handleClose">
        <div style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 20px;border-radius: 10px">
          <el-row>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>老人姓名：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-autocomplete
                size="small"
                v-model="state_notoutman"
                style="width: 80%"
                :fetch-suggestions="querySearchAsync_notoutman"
                placeholder="请输入姓名"
                @select="handleSelect_notoutman"
              ></el-autocomplete>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>登记人：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-input size="small" :value="uname" style="width: 80%" disabled></el-input>
            </el-col>
          </el-row>

          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>陪同人员姓名：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-input size="small" v-model="insertOutregistrationvalue.outAccompanyname" style="width: 80%"></el-input>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>陪同人员电话：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-input size="small" v-model="insertOutregistrationvalue.outAccompanyphone" onkeyup="value=value.replace(/[^\d]/g,'')" style="width: 80%"></el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>计划返回时间：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-date-picker
                style="width: 80%"
                size="small"
                value-format="yyyy-MM-dd HH:mm:ss"
                v-model="insertOutregistrationvalue.outReturnestimate"
                type="datetime"
                placeholder="选择日期时间">
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>外出原因：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                :rows="3"
                placeholder="请输入内容"
                v-model="insertOutregistrationvalue.outReason">
              </el-input>
            </el-col>
          </el-row>
          <div style="text-align: center;margin-top: 20px">
            <span slot="footer" class="dialog-footer">
              <el-button type="info" round size="small" @click="closeinsertOutregistration">取 消</el-button>
              <el-button type="success" round size="small" @click="insertOutregistration">确 定</el-button>
            </span>
          </div>
        </div>
      </el-dialog>
    </div>
    <div>
      <el-dialog
        :visible.sync="dialogVisible_update"
        width="25%"
        top="12%"
        style="text-align: center;font-size: 12px"
        :before-close="handleClose_update">
        <el-row style="text-align: center">
          <el-tag>延后日期</el-tag>
          <el-date-picker
            style="width: 60%;"
            size="small"
            value-format="yyyy-MM-dd HH:mm:ss"
            v-model="updateOutreturn.updateoutReturnestimate"
            type="datetime"
            placeholder="选择日期时间">
          </el-date-picker>
        </el-row>
          <div style="text-align: center;margin-top: 10px">
            <span slot="footer" class="dialog-footer">
              <el-button type="info" round size="small" @click="updateOutReturnestimateFalse">取 消</el-button>
              <el-button type="success" round size="small" @click="updateOutReturnestimate">确 定</el-button>
            </span>
          </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Outregistration",
    data(){
      return{
        dialogVisible_update:false,
        updateOutreturn:{
          outId:'',
          updateoutReturnestimate:'',
        },
        uname:'',
        total:0,
        pageSize:2,
        current:1,
        outregistrationPage:[],
        /*未外出的老人*/
        notOutMan:[],
        /*新增外出登记*/
        insertOutregistrationvalue:{
            outAccompanyname:'',
            outAccompanyphone:'',
            outReturnestimate:'',
            outReason:'',
            omId:'',
            uid:''
        },
        dialogVisible: false,
        restaurants: [],
        state: '',
        timeout:  null,

        restaurants_notoutman: [],
        state_notoutman: '',
        timeout_notoutman:  null,
        selectOutregistration:{
          tomName:'',
          outtrue:'',
          time:[],
        },
        options:[{
          value: '未返回',
          label: '未返回'
        },{
          value: '已返回',
          label: '已返回'
        }],
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },

      }
    },
    methods:{
      /*显示延期弹窗*/
      updateOutReturnShow(row){
        this.dialogVisible_update=true;
        this.updateOutreturn.outId=row.outId;
      },
      /*修改延期时间*/
      updateOutReturnestimate(){
        this.$axios.post("outregistration/updateOutReturnestimate",this.$qs.stringify({
          outId:this.updateOutreturn.outId,
          outReturnestimate:this.updateOutreturn.updateoutReturnestimate
        }))
        .then(r=>{
          if(r>0){
            this.$message({
              type: 'success',
              message: '老人拒收成功！！',
              duration: 1000,
              showClose: true,
            })
            this.selectByPage();
            this.updateOutReturnestimateFalse();
          }
        })
      },
      updateOutReturnestimateFalse(){
        this.updateOutreturn.updateoutReturnestimate='';
        this.dialogVisible_update=false;
      },

      /*登记返回*/
      updateOutTrue(row){
        this.$axios.post("outregistration/updateOutregistration",this.$qs.stringify({
          outId:row.outId
        }))
        .then(r=>{
          if(r>0){
            this.$message({
              type: 'success',
              message: '老人入狱了~~~',
              duration: 1000,
              showClose: true,
            })
            this.selectByPage();
          }
        })
      },
      /*查询未外出的老人*/
      showdialogVisible(){
        this.dialogVisible=true;
        this.uname=localStorage.getItem("uname");
        let mm=[];
        this.$axios.post("oldman/selectNotOut")
        .then(r=>{
          r.forEach(item=>{
            let ss={"value":item.tomName,"address":item.omId};
            mm.push(ss);
          })
        })
        this.restaurants_notoutman=mm;
      },
      querySearchAsync_notoutman(queryString, cb) {
        var restaurants = this.restaurants_notoutman;
        var results = queryString ? restaurants.filter(this.createStateFilter_notoutman(queryString)) : restaurants;
        clearTimeout(this.timeout_notoutman);
        this.timeout_notoutman = setTimeout(() => {
          cb(results);
        });
      },
      createStateFilter_notoutman(queryString) {
        return (state) => {
          return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      handleSelect_notoutman(item) {
        this.insertOutregistrationvalue.omId=item.address;
      },

      /*新增外出记录*/
      insertOutregistration(){
        if(this.insertOutregistrationvalue.outAccompanyname=='' || this.insertOutregistrationvalue.outAccompanyname==null){
          this.$message({
            type: 'warning',
            message: '请输入正确的姓名',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else if(this.insertOutregistrationvalue.outAccompanyphone.length!=11){
          this.$message({
            type: 'warning',
            message: '请输入正确的电话号码',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else if(this.insertOutregistrationvalue.outReturnestimate==null ||this.insertOutregistrationvalue.outReturnestimate==''){
          this.$message({
            type: 'warning',
            message: '请输入预计返回时间',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else {
          this.insertOutregistrationvalue.uid=localStorage.getItem("uid");
          this.$axios.post("outregistration/insertOutregistration",this.$qs.stringify({
            outAccompanyname:this.insertOutregistrationvalue.outAccompanyname,
            outAccompanyphone:this.insertOutregistrationvalue.outAccompanyphone,
            outReturnestimate:this.insertOutregistrationvalue.outReturnestimate,
            outReason:this.insertOutregistrationvalue.outReason,
            omId:this.insertOutregistrationvalue.omId,
            uid:this.insertOutregistrationvalue.uid
          }))
          .then(r=>{
            this.$message({
              type: 'success',
              message: '老人逃跑成功！！！',
              duration: 1000,
              showClose: true,
            })
            this.selectByPage();
            this.closeinsertOutregistration();
          })
        }
      },
      closeinsertOutregistration(){
        this.insertOutregistrationvalue={};
        this.restaurants_notoutman='';
        this.dialogVisible = false;
      },
      /*分页查询*/
      selectByPage(){
        this.$axios.post("outregistration/page",this.$qs.stringify({
          no:this.current,
          size:this.pageSize,
          tomName:this.selectOutregistration.tomName,
          outtrue:this.selectOutregistration.outtrue,
          time:this.selectOutregistration.time
        }))
        .then(r=>{
          this.outregistrationPage=r.list;
          this.total=r.total;
          console.log(r);
        })
      },
      /*查询所有老人*/
      selectOldmanAll(){
        let mm=[];
        this.$axios.post("oldman/selectAll")
          .then(r=>{
            r.forEach(item=>{
              let ss={"value":item.tomName,"address":item.omId};
              mm.push(ss);
            })
          })
        return mm;
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
        this.selectOutregistration.tomName=item.value;
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
      /*关闭新增弹窗*/
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
            this.closeinsertOutregistration();
          })
          .catch(_ => {});
      },
      /*关闭修改时间弹窗*/
      handleClose_update(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
    },
    created() {
      this.selectByPage();
      this.restaurants=this.selectOldmanAll();
    }
  }
</script>

<style>
  .outregistration{
    overflow: hidden;
    overflow-y: auto;
    -webkit-overflow-scrolling: touch;
    background: #dcdcdc36;
    height: 100%;
  }
  .outregistration .outnei{
    background: white;
    margin: 10px;
    padding: 10px;
    border-radius: 5px;
    height: 82%;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
  .outregistration .cell{
    text-align: center;
    font-size: 12px;
  }
  .outregistration textarea {
    font-family: Arial, Helvetica, sans-serif;
    resize: none;
  }
  .outregistration .el-dialog__body{
    padding:0px 20px 30px
  }
  .outregistration .el-dialog{
    border-radius: 10px;
  }
  .outregistration .el-dialog__headerbtn{
    top: 10px;
  }
</style>

