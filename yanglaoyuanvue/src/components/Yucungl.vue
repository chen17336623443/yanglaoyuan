<template>
    <div class="ycgl">
      <div style="background: white;padding: 10px;">
        <div>
          <el-button type="success" size="small" icon="el-icon-plus" @click="rzcz">入住老人账号充值</el-button>
        </div>
        <div style="margin: 10px 0px;">
          <el-input
            placeholder="老人姓名"
            v-model="selet.omname"
            style="width: 200px;"
            clearable>
          </el-input>
          <el-input
            placeholder="手机号"
            v-model="selet.phone"
            @blur="yanzhe(1)"
            style="width: 200px;"
            clearable>
          </el-input>
          <el-input
            placeholder="身份证"
            v-model="selet.card"
            @blur="yanzhe(2)"
            style="width: 200px;"
            clearable>
          </el-input>
          <el-button type="primary" size="small" icon="el-icon-search" @click="select()">查询</el-button>
          <el-button type="warning" size="small" icon="el-icon-refresh" style="margin-left: 0px;" @click="cz()">重置</el-button>
        </div>
        <div style="border: 1px solid #EBEEF5">
          <el-table
            ref="multipleTable"
            :data="deps"
            tooltip-effect="dark"
            style="width: 100%">
            <el-table-column
              type="index"
              header-align="center"
              align="center"
              width="60">
            </el-table-column>
            <el-table-column
              label="老人姓名">
              <template slot-scope="scope">
                {{scope.row.oldmanByOmId.tomName}}
              </template>
            </el-table-column>
            <el-table-column
              label="手机号">
              <template slot-scope="scope">
                <i class="fa fa-volume-control-phone"></i>
                {{scope.row.oldmanByOmId.tomNumber}}
              </template>
            </el-table-column>
            <el-table-column
              label="身份证号"
              header-align="center"
              align="center"
              width="210">
              <template slot-scope="scope">
                <i class="fa fa-vcard"></i>
                {{scope.row.oldmanByOmId.tomCard}}
              </template>
            </el-table-column>
            <el-table-column
              label="床位号"
              header-align="center"
              align="center"
              width="210">
              <template slot-scope="scope">
                <span v-if="scope.row.oldmanByOmId.beds.length>0">
                  {{scope.row.oldmanByOmId.beds[0].ldh+'—'+scope.row.oldmanByOmId.beds[0].fjh+'—'+scope.row.oldmanByOmId.beds[0].bid}}
                </span>
                <span v-else>
                  暂无
                </span>
              </template>
            </el-table-column>
            <el-table-column
              label="账号存款余额">
              <template slot-scope="scope">
                <i class="fa fa-cny"></i>
                {{scope.row.depMoney}}
              </template>
            </el-table-column>
            <el-table-column
              prop="address"
              header-align="center"
              align="center"
              label="操作">
              <template slot-scope="scope">
                <el-button type="primary" size="mini" icon="el-icon-search" plain @click="ycxq(scope.row)">详情</el-button>
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
          title="账号充值"
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
                  <td class="sj">
                    <el-input
                      placeholder="账号余额"
                      v-model="zhye"
                      style="width: 200px;"
                      :disabled="true"
                      clearable>
                    </el-input>
                  </td>
                  <td class="st">预存时间:</td>
                  <td class="sj">
                    <el-input
                      placeholder="预存时间"
                      v-model="ycdate"
                      :disabled="true"
                      style="width: 200px;"
                      clearable>
                    </el-input>
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
                  <td class="st">交费人:</td>
                  <td class="sj">
                    <el-input
                      placeholder="交费人"
                      v-model="form.jfr"
                      style="width: 200px;"
                      clearable>
                    </el-input>
                  </td>
                  <td class="st">收费人:</td>
                  <td class="sj">
                    <el-input
                      placeholder="收费人"
                      v-model="form.sfr"
                      :disabled="true"
                      style="width: 200px;"
                      clearable>
                    </el-input>
                  </td>
                </tr>
                <tr style="height: 40px;">
                  <td class="st">交费方式:</td>
                  <td class="sj" colspan="3">
                    <el-radio-group v-model="form.jffs" size="small">
                      <el-radio-button label="现金"></el-radio-button>
                      <el-radio-button label="微信" ></el-radio-button>
                      <el-radio-button label="支付宝"></el-radio-button>
                      <el-radio-button label="银联"></el-radio-button>
                      <el-radio-button label="刷卡"></el-radio-button>
                      <el-radio-button label="其他"></el-radio-button>
                    </el-radio-group>
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
        <el-dialog width="60%" center title="预存详情" :visible.sync="yucxq">
          <el-table :data="cons">
            <el-table-column property="cosMoney" label="预存金额" width="150"></el-table-column>
            <el-table-column property="cosDate" label="收费时间" width="200"></el-table-column>
            <el-table-column property="userByUid.uname" label="收费员"></el-table-column>
            <el-table-column property="cosExplain" label="说明" width="200"></el-table-column>
            <el-table-column property="cosRemarks" label="备注"></el-table-column>
          </el-table>
        </el-dialog>
      </div>
    </div>
</template>

<script>
    export default {
      data() {
        return {
          current:1,
          pageSize:5,
          total:0,
          http:'http://localhost:8089/',
          deps:[],
          dep:{},
          selet:{
            omname:null,
            phone:null,
            card:null
          },
          centerDialogVisible: false,
          form:{
            name:'',
            jmoney:'',
            jfr:'',
            sfr:localStorage.getItem("uname"),
            jffs:'现金',
            bz:''
          },
          uid:localStorage.getItem("uid"),
          zhye:'',
          ycdate:'',
          cwh:'',
          olds:[],
          cons:[],
          yucxq:false,
          zmoney:0,
        }
      },
      methods: {
        ycxq(row){
          this.yucxq = true;
          let s = {
            omid:row.oldmanByOmId.omId,
            type:'费用预存'
          }
          let ppp = this.$qs.stringify(s);
          this.$axios.post('costflow/byomid',ppp)
            .then(r=>{
              console.log("cons:",r);
              this.cons = r;
            })
            .catch(e=>{

            })
        },
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
            jffs:'现金',
            bz:''
          }
          this.form = a;
          this.zhye = '';
          this.ycdate = '';
        },
        xuanz(){
          let param={
            omid:this.form.name
          };
          let ppp = this.$qs.stringify(param);
          this.$axios.post('deposit/byid',ppp)
            .then(r=>{
              console.log("dep:",r);
              this.zhye = r.depMoney;
              this.ycdate = r.depDate;
              let a = r.oldmanByOmId.beds.length;
              if(a>0){
                this.cwh = r.oldmanByOmId.beds[0].ldh+'—'+r.oldmanByOmId.beds[0].fjh+'—'+r.oldmanByOmId.beds[0].bid;
              }else{
                this.cwh = '暂无';
              }
            })
            .catch(e=>{

            })
        },
        updyuc(){
          this.centerDialogVisible = false;
          let s = {
            omid: this.form.name,
            zmoney: parseFloat(this.form.jmoney)+parseFloat(this.zhye),
            jmoney: parseFloat(this.form.jmoney),
            jfr: this.form.jfr,
            sfr: this.uid,
            jffs: this.form.jffs,
            bz: this.form.bz,
            sfqf: 0,
            no:1,
            size:this.pageSize
          }
          if(parseInt(s.jmoney)>parseInt(s.zmoney)){
            s.sfqf = 1
          }
          console.log(s);
          let ppp = this.$qs.stringify(s);
          this.$axios.post('deposit/upd',ppp)
            .then(r=>{
              console.log("dep:",r.list);
              this.deps = r.list;
              this.$message({
                message: '预存成功，已进入余额！！！',
                type: 'success'
              });
              this.total = r.total;
              this.current = 1;
              this.sjqk();
            })
            .catch(e=>{

            })
        },
        cz(){
          let s = {
            omname:null,
            phone:null,
            card:null
          }
          this.selet = s;
        },
        yanzhe(i){
          if(i==1){
            if(this.selet.phone.toString().length!=11){
              this.$message({
                message: '请输入正确的手机号！！！',
                type: 'warning'
              });
              this.selet.phone = null;
            }
          }else {
            if(this.selet.card.toString().length!=18){
              this.$message({
                message: '请输入正确的身份证号！！！',
                type: 'warning'
              });
              this.selet.card = null;
            }
          }

        },
        rzcz(){
          this.centerDialogVisible = true;
        },
        select(){
          console.log(this.selet);
          let s = {
            name: this.selet.omname,
            phone: this.selet.phone,
            card: this.selet.card,
            no:1,
            size:this.pageSize
          }
          let ppp = this.$qs.stringify(s);
          this.$axios.post('deposit/pagers',ppp)
            .then(r=>{
              console.log("dep:",r.list);
              this.deps = r.list;
              let m = 0;
              this.deps.forEach((v, i) => {
                m += v.depMoney
              })
              this.zmoney = m;
              this.total = r.total;
              this.current = 1;
            })
            .catch(e=>{

            })
        },
        selects(){
          console.log(this.selet);
          let s = {
            name: this.selet.omname,
            phone: this.selet.phone,
            card: this.selet.card,
            no:this.current,
            size:this.pageSize
          }
          let ppp = this.$qs.stringify(s);
          this.$axios.post('deposit/pagers',ppp)
            .then(r=>{
              console.log("dep:",r.list);
              this.deps = r.list;
              let m = 0;
              this.deps.forEach((v, i) => {
                m += v.depMoney
              })
              this.zmoney = m;
              this.total = r.total
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
        getdep(){
          let param={
            no:this.current,
            size:this.pageSize
          };
          let ppp = this.$qs.stringify(param);
          this.$axios.post('deposit/pager',ppp)
            .then(r=>{
              console.log("dep:",r.list);
              this.deps = r.list;
              let m = 0;
              this.deps.forEach((v, i) => {
                m += v.depMoney
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
          if(this.selet.omname!=null || this.selet.phone!=null || this.selet.card!=null){
            this.selects();
          }else{
            this.select();
          }
        },
        /* pageSize 改变时会触发*/
        handleSizeChange(pagesize){
          this.pageSize=pagesize;
          if(this.selet.omname!=null || this.selet.phone!=null || this.selet.card!=null){
            this.selects();
          }else{
            this.select();
          }
        }
      },
      mounted() {
        this.getdep();
        this.getoldm();
      }
    }
</script>

<style>
  .ycgl{
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
