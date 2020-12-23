<template>
  <div class="accident">
    <div>
      <el-row style="background: white;border-radius: 5px;margin: 10px;padding: 10px">
        <el-col style="font-family: 微软雅黑;">
          <el-tag type="success" style="font-size: 12px;">意外记录</el-tag>
        </el-col>
      </el-row>
    </div>
    <div class="outnei">
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px">
        <el-col :span="5">
          <span style="font-size: 14px">老人姓名：</span>
          <el-input size="small" style="width: 60%" v-model="selectAccident.tomname" clearable placeholder="请输入姓名"></el-input>
        </el-col>
        <el-col :span="5">
          <span style="font-size: 14px">床位号：</span>
          <el-input size="small" clearable style="width: 60%" v-model="selectAccident.bname" placeholder="请输入床位号"></el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="success" round size="small" @click="selectByPage">查询</el-button>
          <el-button type="info" round size="small" style="margin-left: 10px" @click="insertAccidentShow">意外登记</el-button>
        </el-col>
      </el-row>
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px">
        <el-table
          :data="accidentPage"
          border
          style="width: 100%;border-radius: 5px">
          <el-table-column
            prop="accId"
            width="80"
            label="编号">
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomName"
            width="120"
            label="老人姓名">
          </el-table-column>
          <el-table-column
            prop=""
            width="120"
            label="床位号">
          </el-table-column>
          <el-table-column
            prop="accTime"
            width="150"
            label="事故发生时间">
          </el-table-column>
          <el-table-column
            prop="accType"
            width="100"
            label="事故类型">
            <template slot-scope="x">
              <span v-for="item in options" v-if="x.row.accType==item.value">
                {{item.label}}
              </span>
            </template>
          </el-table-column>
          <el-table-column
            prop="accReason"
            label="事故原因">
          </el-table-column>
          <el-table-column
            prop="accDescribe"
            label="事故描述">
          </el-table-column>
          <el-table-column
            width="150"
            label="操作">
            <template slot-scope="x">
              <el-button type="success" size="mini" plain @click="updateAccidentShow(x.row)">修改</el-button>
              <el-button type="warning" size="mini" plain @click="deleteAccident(x.row)" >删除</el-button>
            </template>
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
    <!--老人意外新增弹窗-->
    <div>
      <el-dialog
        top="60px"
        :visible.sync="InsertDialogVisible"
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
                v-model="state"
                style="width: 80%"
                :fetch-suggestions="querySearchAsync"
                placeholder="请输入姓名"
                @select="handleSelect"
              ></el-autocomplete>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>发生时间：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-date-picker
                style="width: 80%;"
                size="small"
                value-format="yyyy-MM-dd HH:mm:ss"
                v-model="insertAccidentParam.accTime"
                type="datetime"
                placeholder="选择日期时间">
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>负责人：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-input size="small" :value="uname" disabled style="width: 80%"></el-input>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>事故类型：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-select clearable placeholder="请选择" v-model="insertAccidentParam.accType" size="small" style="width: 80%">
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
              <span>事故原因：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                v-model="insertAccidentParam.accReason"
                :rows="3"
                placeholder="请输入内容">
              </el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>事故描述：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                v-model="insertAccidentParam.accDescribe"
                :rows="3"
                placeholder="请输入内容">
              </el-input>
            </el-col>
          </el-row>
          <div style="text-align: center;margin-top: 20px">
            <span slot="footer" class="dialog-footer">
              <el-button type="info" round size="small" @click="insertAccidentClose">取 消</el-button>
              <el-button type="success" round size="small" @click="insertAccident">确 定</el-button>
            </span>
          </div>
        </div>
      </el-dialog>
    </div>
    <!--老人意外修改弹窗-->
    <div>
      <el-dialog
        top="60px"
        :visible.sync="UpdateDialogVisible"
        width="50%"
        style="text-align: center;font-size: 12px"
        :before-close="handleClose_update">
        <div style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 20px;border-radius: 10px">
          <el-row>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>老人姓名：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-input
                size="small"
                v-model="selectAccidentUpdate.oldmanByOmId.tomName"
                disabled
                style="width: 80%"
              ></el-input>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>发生时间：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-date-picker
                style="width: 80%;"
                size="small"
                value-format="yyyy-MM-dd HH:mm:ss"
                v-model="selectAccidentUpdate.accTime"
                type="datetime"
                placeholder="选择日期时间">
              </el-date-picker>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>负责人：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-input size="small" v-model="selectAccidentUpdate.userByUid.uname" disabled style="width: 80%"></el-input>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>事故类型：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-select clearable placeholder="请选择" v-model="selectAccidentUpdate.accType" size="small" style="width: 80%">
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
              <span>事故原因：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                v-model="selectAccidentUpdate.accReason"
                :rows="3"
                placeholder="请输入内容">
              </el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>事故描述：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                v-model="selectAccidentUpdate.accDescribe"
                :rows="3"
                placeholder="请输入内容">
              </el-input>
            </el-col>
          </el-row>
          <div style="text-align: center;margin-top: 20px">
            <span slot="footer" class="dialog-footer">
              <el-button type="info" round size="small" @click="updateAccidentClose">取 消</el-button>
              <el-button type="success" round size="small" @click="updateAccident">确 定</el-button>
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
        uname:'',
        total:0,
        pageSize:2,
        current:1,
        /*来访分页载体*/
        accidentPage:[],

        /*搜索参数*/
        selectAccident:{
          tomname:'',
          bname:''
        },
        /*新增意外参数*/
        insertAccidentParam:{
          omid:'',
          uid:'',
          accType:'',
          accReason:'',
          accDescribe:'',
          accTime:''
        },
        InsertDialogVisible:false,
        UpdateDialogVisible:false,
        selectAccidentUpdate:{
          accDescribe:'',
          accId:'',
          accReason:'',
          accTime:'',
          accType:'',
          oldmanByOmId:{
            omId:'',
            tomName:''
          },
          userByUid:{
            uid:'',
            uname:''
          }
        },
        restaurants: [],
        state: '',
        timeout:  null,
        options:[{
          value: '1',
          label: '意外摔倒'
        },{
          value: '2',
          label: '打群架'
        },{
          value: '3',
          label: '抢老奶奶'
        },{
          value: '4',
          label: '抢饭'
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
      /*删除事故*/
      deleteAccident(row){
        this.$axios.post("accident/deleteAccident",this.$qs.stringify({
          accid:row.accId
        }))
        .then(r=>{
          if (r>0){
            this.$message({
              type: 'success',
              message: '事故已删除！！',
              duration: 1000,
              showClose: true,
            })
          }
          this.selectByPage();
        })
      },
      /*修改事故*/
      updateAccident(){
        if (this.selectAccidentUpdate.accTime==null){
          this.$message({
            type: 'waring',
            message: '时间不能为空！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else if (this.selectAccidentUpdate.accType==null){
          this.$message({
            type: 'waring',
            message: '类型不能为空！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else{
          this.$axios.post("accident/updateAccident",this.$qs.stringify({
            accid:this.selectAccidentUpdate.accId,
            accdescribe:this.selectAccidentUpdate.accDescribe,
            accreason:this.selectAccidentUpdate.accReason,
            acctime:this.selectAccidentUpdate.accTime,
            acctype:this.selectAccidentUpdate.accType
          }))
          .then(r=>{
            if (r>0){
              this.$message({
                type: 'success',
                message: '意外发生成功！！',
                duration: 1000,
                showClose: true,
              })
              this.UpdateDialogVisible=false;
              this.selectByPage();
            }
          })
        }
      },
      /*关闭事故*/
      updateAccidentClose(){
        this.UpdateDialogVisible=false;
        this.selectByPage();
      },
      /*显示修改弹窗*/
      updateAccidentShow(row){
        this.UpdateDialogVisible=true;
        this.$axios.post("accident/selectByaccid",this.$qs.stringify({
          accid:row.accId
        }))
        .then(r=>{
          this.selectAccidentUpdate=r;
        })
      },
      /*新增事故*/
      insertAccident(){
        if (this.insertAccidentParam.omid==null){
          this.$message({
            type: 'waring',
            message: '老人不能为空！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else if (this.insertAccidentParam.accType==null){
          this.$message({
            type: 'waring',
            message: '类型不能为空！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else{
          this.$axios.post("accident/insertAccident",this.$qs.stringify({
            omid:this.insertAccidentParam.omid,
            uid:this.insertAccidentParam.uid,
            accType:this.insertAccidentParam.accType,
            accReason:this.insertAccidentParam.accReason,
            accDescribe:this.insertAccidentParam.accDescribe,
            accTime:this.insertAccidentParam.accTime
          }))
          .then(r=>{
            if (r>0){
              this.$message({
                type: 'success',
                message: '发生意外了！！',
                duration: 1000,
                showClose: true,
              })
              this.insertAccidentClose();
            }
          })
        }
      },
      /*关闭新增弹窗*/
      insertAccidentClose(){
        this.insertAccidentParam={};
        this.InsertDialogVisible=false;
        this.state='';
      },
      /*新增弹窗显示*/
      insertAccidentShow(){
        this.InsertDialogVisible=true;
        this.insertAccidentParam.uid=localStorage.getItem("uid");
        this.uname=localStorage.getItem("uname");
      },
      /*分页模糊查询*/
      selectByPage(){
        this.$axios.post("accident/selectByPage",this.$qs.stringify({
          no:this.current,
          size:this.pageSize,
          tomname:this.selectAccident.tomname
        }))
        .then(r=>{
          this.accidentPage=r.list;
          this.total=r.total;
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
        this.insertAccidentParam.omid=item.address;
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
          })
          .catch(_ => {});
      },
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
  .accident{
    overflow: hidden;
    overflow-y: auto;
    -webkit-overflow-scrolling: touch;
    background: #dcdcdc36;
    height: 100%;
  }
  .accident .outnei{
    background: white;
    margin: 10px;
    padding: 10px;
    border-radius: 5px;
    height: 82%;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
  .accident .cell{
    text-align: center;
    font-size: 12px;
  }
  .accident textarea {
    font-family: Arial, Helvetica, sans-serif;
    resize: none;
  }
  .accident .el-dialog__body{
    padding:0px 20px 30px
  }
  .accident .el-dialog{
    border-radius: 10px;
  }
  .accident .el-dialog__headerbtn{
    top: 10px;
  }
</style>

