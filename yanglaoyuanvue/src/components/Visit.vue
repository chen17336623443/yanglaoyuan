<template>
  <div class="visit">
    <div>
      <el-row style="background: white;border-radius: 5px;margin: 10px;padding: 10px">
        <el-col style="font-family: 微软雅黑;">
          <el-tag type="success" style="font-size: 12px;">探视来访</el-tag>
        </el-col>
      </el-row>
    </div>
    <div class="outnei">
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px">
        <el-col :span="5">
          <span style="font-size: 14px">来访人姓名：</span>
          <el-input size="small" v-model="selectVisit.vname" style="width: 60%" clearable placeholder="请输入姓名"></el-input>
        </el-col>
        <el-col :span="5">
          <span style="font-size: 14px">老人姓名：</span>
          <el-input
            size="small"
            v-model="selectVisit.tomname"
            clearable
            style="width: 60%"
            placeholder="请输入姓名"
          ></el-input>
        </el-col>

        <el-col :span="10">
          <span style="font-size: 14px">来访时间：</span>
          <el-date-picker
            size="small"
            style="width: 80%"
            v-model="selectVisit.visittime"
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
          <el-button type="info" round size="small" style="margin-left: 10px" @click="insertVisitShow">来访登记</el-button>
        </el-col>
      </el-row>
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px">
        <el-table
          :data="visitPage"
          border
          style="width: 100%;border-radius: 5px">
          <el-table-column
            fixed
            prop="vId"
            width="100"
            label="编号">
          </el-table-column>
          <el-table-column
            prop="vName"
            width="100"
            label="来访人姓名">
          </el-table-column>
          <el-table-column
            prop="oldmanByOmId.tomName"
            width="100"
            label="老人姓名">
          </el-table-column>
          <el-table-column
            prop="vPhone"
            width="120"
            label="联系电话">
          </el-table-column>
          <el-table-column
            width="100"
            label="与老人关系">
            <template slot-scope="x">
              <span v-for="item in options" v-if="x.row.vRelationship==item.value">
                {{item.label}}
              </span>
            </template>
          </el-table-column>
          <el-table-column
            prop="vReason"
            width="150"
            label="来访缘由">
          </el-table-column>
          <el-table-column
            prop="vVisittime"
            width="150"
            label="来访时间">
          </el-table-column>
          <el-table-column
            prop="vLeavetime"
            width="150"
            label="离开时间">
          </el-table-column>
          <el-table-column
            prop="userByUid.uname"
            width="100"
            label="登记人">
          </el-table-column>
          <el-table-column
            prop="vRemarks"
            width="130"
            label="备注">
          </el-table-column>
          <el-table-column
            fixed="right"
            width="150"
            label="操作">
            <template slot-scope="x">
              <el-button type="warning" size="mini" plain @click="updateVisitShow(x.row)" v-if="x.row.vLeavetime!=null" disabled>修改</el-button>
              <el-button type="warning" size="mini" plain @click="updateVisitShow(x.row)" v-if="x.row.vLeavetime==null">修改</el-button>
              <el-button type="success" size="mini" plain @click="leaveVisit(x.row)" v-if="x.row.vLeavetime!=null" disabled>离开</el-button>
              <el-button type="success" size="mini" plain @click="leaveVisit(x.row)" v-if="x.row.vLeavetime==null">离开</el-button>
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
    <!--来访登记弹窗-->
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
              <span>来访人姓名：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-input size="small" v-model="insertVisitParam.vname" style="width: 80%"></el-input>
            </el-col>
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
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>联系电话：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-input size="small" v-model="insertVisitParam.vphone" onkeyup="value=value.replace(/[^\d]/g,'')" style="width: 80%"></el-input>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>与老人关系：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-select clearable v-model="insertVisitParam.vrelationship" placeholder="请选择" size="small" style="width: 80%">
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
              <span>登记人：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-input size="small" :value="uname" style="width: 80%" disabled></el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>来访缘由：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                v-model="insertVisitParam.vreason"
                :rows="3"
                placeholder="请输入内容">
              </el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>备注：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                v-model="insertVisitParam.vremarks"
                :rows="3"
                placeholder="请输入内容">
              </el-input>
            </el-col>
          </el-row>
          <div style="text-align: center;margin-top: 20px">
            <span slot="footer" class="dialog-footer">
              <el-button type="info" round size="small" @click="clearInsert">取 消</el-button>
              <el-button type="success" round size="small" @click="insertVisit">确 定</el-button>
            </span>
          </div>
        </div>
      </el-dialog>
    </div>
    <!--修改来访记录-->
    <div>
      <el-dialog
        top="60px"
        :visible.sync="UpdateDialogVisible"
        width="50%"
        style="text-align: center;font-size: 12px"
        :before-close="handleClose">
        <div style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 20px;border-radius: 10px">
          <el-row>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>来访人姓名：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-input size="small" v-model="selectVisitUpdate.vName" style="width: 80%"></el-input>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>老人姓名：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-input size="small" v-model="selectVisitUpdate.oldmanByOmId.tomName" style="width: 80%" disabled></el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>联系电话：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-input size="small" v-model="selectVisitUpdate.vPhone" onkeyup="value=value.replace(/[^\d]/g,'')" style="width: 80%"></el-input>
            </el-col>
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>与老人关系：</span>
            </el-col>
            <el-col :span="8" style="text-align: left;line-height: 32px">
              <el-select clearable v-model="selectVisitUpdate.vRelationship" placeholder="请选择" size="small" style="width: 80%">
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
              <span>登记人：</span>
            </el-col>
            <el-col :span="8" style="text-align: left">
              <el-input size="small" v-model="selectVisitUpdate.userByUid.uname" style="width: 80%" disabled></el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>来访缘由：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                v-model="selectVisitUpdate.vReason"
                :rows="3"
                placeholder="请输入内容">
              </el-input>
            </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
            <el-col :span="4" style="text-align: right;line-height: 32px">
              <span>备注：</span>
            </el-col>
            <el-col :span="20" style="text-align: left;">
              <el-input
                style="width: 92%"
                type="textarea"
                v-model="selectVisitUpdate.vRemarks"
                :rows="3"
                placeholder="请输入内容">
              </el-input>
            </el-col>
          </el-row>
          <div style="text-align: center;margin-top: 20px">
            <span slot="footer" class="dialog-footer">
              <el-button type="info" round size="small" @click="updateVisitClose">取 消</el-button>
              <el-button type="success" round size="small" @click="updateVisit">确 定</el-button>
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
        visitPage:[],
        /*根据id查询来访*/
        selectVisitUpdate:{
          vId:'',
          vName:'',
          vPhone:'',
          vReason:'',
          vRelationship:'',
          vRemarks:'',
          oldmanByOmId:{
            omId:'',
            tomName:''
          },
          userByUid:{
            uid:'',
            uname:''
          }
        },
        /*搜索参数*/
        selectVisit:{
          tomname:'',
          vname:'',
          visittime:[]
        },
        /*来访记录新增参数*/
        insertVisitParam:{
          vname:'',
          vphone:'',
          vrelationship:'',
          vreason:'',
          vvisittime:'',
          vremarks:'',
          omid:'',
          uid:''
        },
        InsertDialogVisible:false,
        UpdateDialogVisible:false,
        restaurants: [],
        state: '',
        timeout:  null,
        options:[{
          value: '1',
          label: '父女'
        },{
          value: '2',
          label: '父子'
        },{
          value: '3',
          label: '母女'
        },{
          value: '4',
          label: '母子'
        },{
          value: '5',
          label: '其他'
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
      /*显示修改界面*/
      updateVisitShow(row){
        this.UpdateDialogVisible=true;
        this.$axios.post("visit/selectByvid",this.$qs.stringify({
          vid:row.vId
        }))
        .then(r=>{
          console.log(r,"查询到的单行值");
          this.selectVisitUpdate=r;
          console.log(this.selectVisitUpdate);
          console.log(this.selectVisitUpdate.oldmanByOmId.tomName,"获取的name值");
        })
      },
      /*修改来访记录*/
      updateVisit(){
        if (this.selectVisitUpdate.vName==null || this.selectVisitUpdate.vName==''){
          this.$message({
            type: 'warning',
            message: '请输入探访人姓名！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else if(this.selectVisitUpdate.vPhone.length!=11){
          this.$message({
            type: 'warning',
            message: '请输入正确的电话号码！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else if (this.selectVisitUpdate.vRelationship==null || this.selectVisitUpdate.vRelationship==''){
          this.$message({
            type: 'warning',
            message: '请选择与老人的关系！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else{
          this.$axios.post("visit/updateVisit",this.$qs.stringify({
            vId:this.selectVisitUpdate.vId,
            vName:this.selectVisitUpdate.vName,
            vPhone:this.selectVisitUpdate.vPhone,
            vReason:this.selectVisitUpdate.vReason,
            vRelationship:this.selectVisitUpdate.vRelationship,
            vRemarks:this.selectVisitUpdate.vRemarks
          }))
          .then(r=>{
            if (r>0){
              this.$message({
                type: 'success',
                message: '探视小鬼修改成功！！',
                duration: 1000,
                showClose: true,
              })
              this.updateVisitClose();
            }
          })
        }
      },
      /*关闭修改页面*/
      updateVisitClose(){
        this.UpdateDialogVisible=false;
        this.selectByPage();
      },
      /*离开登记*/
      leaveVisit(row){
        console.log(row);
        this.$axios.post("visit/leaveVisit",this.$qs.stringify({
          vid:row.vId
        }))
        .then(r=>{
          if (r>0){
            this.$message({
              type: 'success',
              message: '探望的孩子离开了！！！',
              duration: 1000,
              showClose: true,
            })
            this.selectByPage();
          }
        })
      },
      /*新增来访记录弹窗显示*/
      insertVisitShow(){
        this.InsertDialogVisible=true;
        this.insertVisitParam.uid=localStorage.getItem("uid");
        this.uname=localStorage.getItem("uname");
      },
      /*新增来访记录*/
      insertVisit(){
        if (this.insertVisitParam.vname==null || this.insertVisitParam.vname==''){
          this.$message({
            type: 'warning',
            message: '请输入姓名！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else if(this.insertVisitParam.vphone.length!=11){
          this.$message({
            type: 'warning',
            message: '请输入正确的电话号码！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else if (this.insertVisitParam.vrelationship==null || this.insertVisitParam.vrelationship==''){
          this.$message({
            type: 'warning',
            message: '请选择与老人的关系！！',
            duration: 1000,
            showClose: true,
          })
          return false;
        }else {
          this.$axios.post("visit/insertVisit",this.$qs.stringify({
            vname:this.insertVisitParam.vname,
            vphone:this.insertVisitParam.vphone,
            vrelationship:this.insertVisitParam.vrelationship,
            vreason:this.insertVisitParam.vreason,
            vremarks:this.insertVisitParam.vremarks,
            omid:this.insertVisitParam.omid,
            uid:this.insertVisitParam.uid,
          }))
            .then(r=>{
              if (r>0){
                this.$message({
                  type: 'success',
                  message: '探望的孩子来了~~',
                  duration: 1000,
                  showClose: true,
                })
                this.clearInsert();
                this.selectByPage();
              }else {
                this.$message({
                  type: 'error',
                  message: '探望的孩子进不来！！！',
                  duration: 1000,
                  showClose: true,
                })
                this.clearInsert();
              }
            })
        }
      },
      /*清除新增来访记录的值*/
      clearInsert(){
        this.InsertDialogVisible=false;
        this.insertVisitParam={};
        this.state='';
      },
      /*分页模糊查询*/
      selectByPage(){
        console.log(this.selectVisit.visittime);
        this.$axios.post("visit/selectByPage",this.$qs.stringify({
          no:this.current,
          size:this.pageSize,
          tomname:this.selectVisit.tomname,
          vname:this.selectVisit.vname,
          visittime:this.selectVisit.visittime
        }))
        .then(r=>{
          this.visitPage=r.list;
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
        this.insertVisitParam.omid=item.address;
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
    },
    created() {
      this.selectByPage();
      this.restaurants=this.selectOldmanAll();
    }
  }
</script>

<style>
  .visit{
    overflow: hidden;
    overflow-y: auto;
    -webkit-overflow-scrolling: touch;
    background: #dcdcdc36;
    height: 100%;
  }
  .visit .outnei{
    background: white;
    margin: 10px;
    padding: 10px;
    border-radius: 5px;
    height: 82%;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
  .visit .cell{
    text-align: center;
    font-size: 12px;
  }
  .visit textarea {
    font-family: Arial, Helvetica, sans-serif;
    resize: none;
  }
  .visit .el-dialog__body{
    padding:0px 20px 30px
  }
  .visit .el-dialog{
    border-radius: 10px;
  }
  .visit .el-dialog__headerbtn{
    top: 10px;
  }
</style>

