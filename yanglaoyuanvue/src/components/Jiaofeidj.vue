<template>
  <div class="jfdj">
    <div style="background: white;padding: 10px;">
      <div>
        <el-button type="success" size="small" icon="el-icon-plus" @click="addjf()">交费</el-button>
      </div>
      <div style="margin: 10px 0px;">
        <el-input
          placeholder="老人姓名"
          v-model="selet.omname"
          style="width: 200px;"
          clearable>
        </el-input>
        <el-date-picker
          v-model="selet.dgdate"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          value-format="yyyy-MM-dd"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions">
        </el-date-picker>
        <el-button type="primary" size="small" icon="el-icon-search" @click="select()">查询</el-button>
        <el-button type="warning" size="small" icon="el-icon-refresh" style="margin-left: 0px;" @click="cz()">重置</el-button>
      </div>
      <div style="border: 1px solid #EBEEF5">
        <el-table
          ref="multipleTable"
          :data="regs"
          tooltip-effect="dark"
          style="width: 100%">
          <el-table-column
            type="index"
            header-align="center"
            align="center"
            width="60">
          </el-table-column>
          <el-table-column
            label="老人姓名"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              {{scope.row.oldmanByOmId.tomName}}
            </template>
          </el-table-column>
          <el-table-column
            label="床位号"
            header-align="center"
            align="center">
            <template slot-scope="scope">

            </template>
          </el-table-column>
          <el-table-column
            label="交费金额（元）"
            header-align="center"
            align="center"
            width="120">
            <template slot-scope="scope">
              <i class="fa fa-cny"></i>
              {{scope.row.regMoney}}
            </template>
          </el-table-column>
          <el-table-column
            label="交费时间"
            header-align="center"
            align="center"
            width="200">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              {{scope.row.regDate}}
            </template>
          </el-table-column>
          <el-table-column
            label="交费人"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              <el-tag>{{scope.row.regPay}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="支付方式"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              {{scope.row.regPaytype}}
            </template>
          </el-table-column>
          <el-table-column
            label="登记人"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              <el-tag>{{scope.row.userByUid.uname}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="备注">
            <template slot-scope="scope">
              {{scope.row.regRemarks}}
            </template>
          </el-table-column>
        </el-table>
        <div style="padding: 10px;color: #606266">
          <el-tag effect="dark">
            合计：<i class="fa fa-cny"></i> {{zmoney}}
          </el-tag>
        </div>
      </div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 10px;"
        :current-page="current"
        :page-sizes="[1, 2, 3, 4 ,5]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
      <el-dialog
        title="交费"
        :visible.sync="centerDialogVisible"
        width="45%"
        center
        :before-close="handleClose">
        <div>
          <el-form ref="form" :model="form" label-width="80px">
            <table width="100%" style="">
              <tr style="height: 40px;">
                <td class="st">老人姓名:</td>
                <td class="sj">
                  <el-select v-model="form.name" filterable @change="xuanz()" placeholder="老人姓名">
                    <el-option
                      v-for="i in olds"
                      :key="i.id"
                      :label="i.name"
                      :value="i.id">
                    </el-option>
                  </el-select>
                </td>
                <td class="st">床位号:</td>
                <td class="sj">
                  <el-input
                    placeholder="床位号"
                    v-model="cwh"
                    :disabled="true"
                    style="width: 200px;"
                    clearable>
                  </el-input>
                </td>
              </tr>
              <tr style="height: 40px;">
                <td class="st">账号余额:</td>
                <td class="sj" colspan="3">
                  <el-input
                    placeholder="账号余额"
                    v-model="zhye"
                    style="width: 300px;"
                    :disabled="true"
                    clearable>
                  </el-input>
                </td>
              </tr>
              <tr style="height: 40px;">
                <td class="st">应缴金额:</td>
                <td class="sj" colspan="3">
                  <el-input
                    placeholder="应缴金额"
                    v-model="yjmoney"
                    style="width: 300px;"
                    :disabled="true"
                    clearable>
                  </el-input>
                  <el-button type="primary" icon="el-icon-document" size="mini">查看账单</el-button>
                </td>
              </tr>
              <tr style="height: 40px;">
                <td class="st">交费金额:</td>
                <td class="sj" colspan="3">
                  <el-input
                    placeholder="交费金额"
                    type="number"
                    min="0"
                    v-model="form.jmoney"
                    style="width: 400px;"
                    clearable>
                  </el-input>
                </td>
              </tr>
              <tr style="height: 40px;">
                <td class="st">交费方式:</td>
                <td class="sj" colspan="3" v-if="this.zff == true">
                  <el-radio-group v-model="form.jffs" size="small" @change="jffs()">
                    <el-radio-button label="余额"></el-radio-button>
                    <el-radio-button label="现金"></el-radio-button>
                    <el-radio-button label="微信" ></el-radio-button>
                    <el-radio-button label="支付宝"></el-radio-button>
                    <el-radio-button label="银联"></el-radio-button>
                    <el-radio-button label="刷卡"></el-radio-button>
                    <el-radio-button label="其他"></el-radio-button>
                  </el-radio-group>
                </td>
                <td class="sj" colspan="3" v-else>
                  <el-radio-group v-model="form.jffs" size="small" @change="jffs()">
                    <el-radio-button label="余额" disabled ></el-radio-button>
                    <el-radio-button label="现金"></el-radio-button>
                    <el-radio-button label="微信" ></el-radio-button>
                    <el-radio-button label="支付宝"></el-radio-button>
                    <el-radio-button label="银联"></el-radio-button>
                    <el-radio-button label="刷卡"></el-radio-button>
                    <el-radio-button label="其他"></el-radio-button>
                  </el-radio-group>
                </td>
              </tr>
              <tr style="height: 40px;">
                <td class="st">交费人:</td>
                <td class="sj" v-if="this.sfsr == true">
                  <el-input
                    placeholder="交费人"
                    v-model="form.jfr"
                    style="width: 200px;"
                    clearable>
                  </el-input>
                </td>
                <td class="sj" v-else>
                  <el-input
                    placeholder="交费人"
                    v-model="form.jfr"
                    :disabled="true"
                    style="width: 200px;"
                    clearable>
                  </el-input>
                </td>
                <td class="st">登记人:</td>
                <td class="sj">
                  <el-input
                    placeholder="登记人"
                    v-model="form.sfr"
                    :disabled="true"
                    style="width: 200px;"
                    clearable>
                  </el-input>
                </td>
              </tr>
              <tr style="height: 62px;">
                <td class="st">备注:</td>
                <td class="sj" colspan="3">
                  <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入内容"
                    v-model="form.bz">
                  </el-input>
                </td>
              </tr>
            </table>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button @click="quxiao()">取 消</el-button>
            <el-button type="primary" @click="updyuc()">确 定</el-button>
          </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
    export default {
      data(){
        return {
          current:1,
          pageSize:5,
          total:0,
          regs:[],
          zmoney:0,
          selet:{
            omname:null,
            dgdate: null,
          },
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
          centerDialogVisible: false,
          form:{
            name:'',
            jmoney:'',
            jfr:'',
            sfr:localStorage.getItem("uname"),
            jffs:'余额',
            bz:''
          },
          uid:localStorage.getItem("uid"),
          zhye:0.00,
          cwh:'',
          yjmoney:0.00,
          olds:[],
          sfsr:true,
          zff:true,
        }
      },
      methods:{
        quxiao(){
          this.centerDialogVisible = false
          this.sjqk();
        },
        sjqk(){
          let a = {
            name:'',
            jmoney:'',
            jfr:'',
            sfr:localStorage.getItem("uname"),
            jffs:'余额',
            bz:''
          }
          this.form = a;
          this.zhye = '';
          this.yjmoney = 0.00;
        },
        jffs(){
          if(this.form.jffs=="余额"){
            this.form.jfr = '自动扣费';
            this.sfsr = false;
          }else{
            this.sfsr = true;
          }
        },
        updyuc(){
          this.centerDialogVisible = false;
          let s = {
            omid: this.form.name,
            moneyc: parseFloat(this.zhye)-parseFloat(this.form.jmoney),
            jmoney: parseFloat(this.form.jmoney),
            jfr: this.form.jfr,
            sfr: this.uid,
            jffs: this.form.jffs,
            bz: this.form.bz,
            no:1,
            size:this.pageSize
          }
          console.log(s);
          let ppp = this.$qs.stringify(s);
          this.$axios.post('register/add',ppp)
            .then(r=>{
              console.log("regs:",r.list);
              this.regs = r.list;
              let m = 0;
              this.regs.forEach((v, i) => {
                m += v.regMoney
              })
              this.zmoney = m;
              this.total = r.total
            })
            .catch(e=>{

            })
        },
        xuanz(){
          let param={
            omid:this.form.name
          };
          let ppp = this.$qs.stringify(param);
          this.$axios.post('deposit/byid',ppp)
            .then(r=>{
              this.zhye = r.depMoney;
              if(this.zhye<0){
                this.form.jffs = '现金';
                this.zff = false;
                this.jffs();
              }else{
                this.form.jffs = '余额';
                this.zff = true;
                this.jffs();
              }
            })
            .catch(e=>{

            })
        },
        addjf(){
          this.centerDialogVisible = true;
          this.jffs();
        },
        cz(){
          let s = {
            omname:null,
            dgdate: null,
          }
          this.selet = s;
        },
        select(){
          let s = {};
          if(this.selet.dgdate!=null){
            s = {
              name: this.selet.omname,
              kdate: this.selet.dgdate[0],
              jdate: this.selet.dgdate[1],
              no:1,
              size:this.pageSize
            }
          }else{
            s = {
              name: this.selet.omname,
              kdate: '',
              jdate: '',
              no:1,
              size:this.pageSize
            }
          }
          console.log(s);
          let ppp = this.$qs.stringify(s);
          this.$axios.post('register/pagers',ppp)
            .then(r=>{
              console.log("regs:",r.list);
              this.regs = r.list;
              let m = 0;
              this.regs.forEach((v, i) => {
                m += v.regMoney
              })
              this.zmoney = m;
              this.total = r.total
              this.current = 1;
            })
            .catch(e=>{

            })
        },
        selects(){
          let s = {};
          if(this.selet.dgdate!=null){
            s = {
              name: this.selet.omname,
              kdate: this.selet.dgdate[0],
              jdate: this.selet.dgdate[1],
              no:this.current,
              size:this.pageSize
            }
          }else{
            s = {
              name: this.selet.omname,
              kdate: '',
              jdate: '',
              no:this.current,
              size:this.pageSize
            }
          }
          console.log(s);
          let ppp = this.$qs.stringify(s);
          this.$axios.post('register/pagers',ppp)
            .then(r=>{
              console.log("regs:",r.list);
              this.regs = r.list;
              let m = 0;
              this.regs.forEach((v, i) => {
                m += v.regMoney
              })
              this.zmoney = m;
              this.total = r.total;
            })
            .catch(e=>{

            })
        },
        getoldm(){
          this.$axios.post('oldman/selectAll')
            .then(r=>{
              console.log("oldm:",r);
              let oldms = r;
              oldms.forEach(v=>{
                let s = {
                  id:v.omId,
                  name:v.tomName
                }
                this.olds.push(s);
              })
            })
            .catch(e=>{

            })
        },
        getregs(){
          let param={
            no:this.current,
            size:this.pageSize
          };
          let ppp = this.$qs.stringify(param);
          this.$axios.post('register/pager',ppp)
            .then(r=>{
              console.log("regs:",r.list);
              this.regs = r.list;
              let m = 0;
              this.regs.forEach((v, i) => {
                m += v.regMoney
              })
              this.zmoney = m;
              this.total = r.total
            })
            .catch(e=>{

            })
        },
        handleClose(done) {
          this.$confirm('确认关闭？')
            .then(_ => {
              this.sjqk();
              done();
            })
            .catch(_ => {});
        },
        handleCurrentChange(pagerindex){
          //参数是当前页码
          this.current = pagerindex;
          if(this.selet.omname!=null || this.selet.dgdate!=null){
            this.select();
          }else{
            this.selects();
          }
        },
        /* pageSize 改变时会触发*/
        handleSizeChange(pagesize){
          this.pageSize=pagesize;
          if(this.selet.omname!=null || this.selet.dgdate!=null){
            this.select();
          }else{
            this.selects();
          }
        }
      },
      mounted() {
        this.getregs();
        this.getoldm();
      }
    }
</script>

<style>
  .jfdj{
    margin: 10px;
    padding: 10px;
    border-radius: 5px;
    background: whitesmoke;
    height: 530px;
  }
  .st{
    width: 80px;
    text-align: right;
    font-weight: bold;
  }
  .sj{
    padding-left: 10px;
    width: 190px;
  }

  .el-dialog--center .el-dialog__body {
    text-align: initial;
    padding: 0px 20px 3px;
  }

  .el-range-editor.el-input__inner {
    display: -ms-inline-flexbox;
    display: inline-flex;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    padding: 0px 10px;
  }

  .el-input__inner {
    -webkit-appearance: none;
    background-color: #FFF;
    background-image: none;
    border-radius: 4px;
    border: 1px solid #DCDFE6;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    color: #606266;
    display: inline-block;
    font-size: inherit;
    height: 32px;
    line-height: 32px;
    outline: 0;
    padding: 0 15px;
    -webkit-transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    width: 100%;
  }
</style>
