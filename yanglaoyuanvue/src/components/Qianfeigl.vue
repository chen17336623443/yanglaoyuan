<template>
    <div class="qdgl">
      <div style="background: white;padding: 10px;">
        <div style="margin: 10px 0px;">
          <el-input
            placeholder="老人姓名"
            v-model="selet.omname"
            style="width: 180px;margin-bottom: 10px;"
            clearable>
          </el-input>
          <el-input
            placeholder="老人身份证"
            v-model="selet.sfz"
            @blur="yanzhe(1)"
            style="width: 200px;"
            clearable>
          </el-input>
          <el-input
            placeholder="床位号"
            v-model="selet.cwh"
            style="width: 120px;"
            clearable>
          </el-input>
          <el-input
            placeholder="欠费金额大于等于"
            v-model="selet.qfje"
            type="number"
            min="0"
            style="width: 180px;margin-right: 160px;"
            clearable>
          </el-input>
          <el-date-picker
            v-model="selet.dgdate"
            type="daterange"
            align="right"
            unlink-panels
            range-separator="至"
            value-format="yyyy-MM-dd"
            start-placeholder="入住日期"
            end-placeholder="入住日期"
            :picker-options="pickerOptions">
          </el-date-picker>
          <el-select v-model="selet.zt" clearable placeholder="是否缴费" style="width: 110px;">
            <el-option
              v-for="i in qfzt"
              :key="i.v"
              :label="i.lb"
              :value="i.v">
            </el-option>
          </el-select>
          <el-button type="primary" size="small" icon="el-icon-search" @click="select()">查询</el-button>
          <el-button type="warning" size="small" icon="el-icon-refresh" style="margin-left: 0px;" @click="cz()">重置</el-button>
          <el-button type="success" size="small" icon="el-icon-plus" style="margin-left: 0px;">缴费</el-button>
        </div>
        <div style="border: 1px solid #EBEEF5">
          <el-table
            ref="multipleTable"
            :data="arres"
            tooltip-effect="dark"
            @selection-change="handleSelectionChange"
            style="width: 100%">
            <el-table-column
              type="selection"
              width="50"
              header-align="center"
              align="center">
            </el-table-column>
            <el-table-column
              label="老人姓名"
              header-align="center"
              align="center"
              width="100">
              <template slot-scope="scope">
                {{scope.row.oldmanByOmId.tomName}}
              </template>
            </el-table-column>
            <el-table-column
              label="身份证号"
              header-align="center"
              align="center"
              width="180">
              <template slot-scope="scope">
                <i class="fa fa-vcard"></i>
                {{scope.row.oldmanByOmId.tomCard}}
              </template>
            </el-table-column>
            <el-table-column
              label="入住日期"
              header-align="center"
              align="center"
              width="180">
              <template slot-scope="scope">
                {{scope.row.oldmanByOmId.tomCheckIntime}}
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
              label="欠费金额（元）"
              header-align="center"
              align="center"
              width="130">
              <template slot-scope="scope">
                <i class="fa fa-cny"></i>
                {{scope.row.arrMoney}}
              </template>
            </el-table-column>
            <el-table-column
              label="月入住费"
              header-align="center"
              align="center">
              <template slot-scope="scope">
                <i class="fa fa-cny"></i>
                {{scope.row.arrMonth}}
              </template>
            </el-table-column>
            <el-table-column
              label="其他月收费"
              header-align="center"
              align="center">
              <template slot-scope="scope">
                <i class="fa fa-cny"></i>
                {{scope.row.arrOther}}
              </template>
            </el-table-column>
            <el-table-column
              label="是否缴费"
              header-align="center"
              align="center">
              <template slot-scope="scope">
                {{scope.row.arrSfpay}}
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
          zmoney:0,
          arres:[],
          selet:{
            omname:null,
            sfz:null,
            cwh:null,
            qfje:null,
            dgdate: null,
            zt:null
          },
          qfzt:[
            {
              v:'是',
              lb:'是'
            },
            {
              v:'否',
              lb:'否'
            }
          ],
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
        yanzhe(i){
          if(i==1){
            if(this.selet.sfz.toString().length!=18){
              this.$message({
                message: '请输入正确的身份证号！！！',
                type: 'warning'
              });
              this.selet.sfz = null;
            }
          }
        },
        cz(){
          let s = {
            omname:null,
            sfz:null,
            cwh:null,
            qfje:null,
            dgdate: null,
            zt:null
          }
          this.selet = s;
        },
        select(){
          let s = {};
          if(this.selet.dgdate!=null){
            s = {
              name: this.selet.omname,
              sfz: this.selet.sfz,
              cwh: this.selet.cwh,
              qfje: this.selet.qfje,
              kdate: this.selet.dgdate[0],
              jdate: this.selet.dgdate[1],
              zt: this.selet.zt,
              no:1,
              size:this.pageSize
            }
          }else{
            s = {
              name: this.selet.omname,
              sfz: this.selet.sfz,
              cwh: this.selet.cwh,
              qfje: this.selet.qfje,
              kdate: '',
              jdate: '',
              zt: this.selet.zt,
              no:1,
              size:this.pageSize
            }
          }
          console.log(s);
          let ppp = this.$qs.stringify(s);
          this.$axios.post('arrears/pagers',ppp)
            .then(r=>{
              console.log("arre:",r.list);
              this.arres = r.list;
              let m = 0;
              this.arres.forEach((v, i) => {
                m += v.arrMoney
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
              sfz: this.selet.sfz,
              cwh: this.selet.cwh,
              qfje: this.selet.qfje,
              kdate: this.selet.dgdate[0],
              jdate: this.selet.dgdate[1],
              zt: this.selet.zt,
              no:this.current,
              size:this.pageSize
            }
          }else{
            s = {
              name: this.selet.omname,
              sfz: this.selet.sfz,
              cwh: this.selet.cwh,
              qfje: parseFloat(this.selet.qfje),
              kdate: '',
              jdate: '',
              zt: this.selet.zt,
              no:this.current,
              size:this.pageSize
            }
          }
          console.log(s);
          let ppp = this.$qs.stringify(s);
          this.$axios.post('arrears/pagers',ppp)
            .then(r=>{
              console.log("arre:",r.list);
              this.arres = r.list;
              let m = 0;
              this.arres.forEach((v, i) => {
                m += v.arrMoney
              })
              this.zmoney = m;
              this.total = r.total
            })
            .catch(e=>{

            })
        },
        getarre(){
          let param={
            no:this.current,
            size:this.pageSize
          };
          let ppp = this.$qs.stringify(param);
          this.$axios.post('arrears/pager',ppp)
            .then(r=>{
              console.log("arre:",r.list);
              this.arres = r.list;
              let m = 0;
              this.arres.forEach((v, i) => {
                m += v.arrMoney
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
              done();
            })
            .catch(_ => {});
        },
        handleCurrentChange(pagerindex){
          //参数是当前页码
          this.current = pagerindex;
          if(this.selet.omname!=null || this.selet.sfz!=null || this.selet.cwh!=null || this.selet.qfje!=null
            || this.selet.dgdate!=null || this.selet.zt!=null ){
            this.select();
          }else{
            this.selects();
          }
        },
        /* pageSize 改变时会触发*/
        handleSizeChange(pagesize){
          this.pageSize=pagesize;
          if(this.selet.omname!=null || this.selet.sfz!=null || this.selet.cwh!=null || this.selet.qfje!=null
            || this.selet.dgdate!=null || this.selet.zt!=null ){
            this.select();
          }else{
            this.selects();
          }
        },
        handleSelectionChange(val) {
          this.multipleSelection = val;
        }
      },
      mounted() {
        this.getarre();
      }
    }
</script>

<style>
  .qdgl{
    margin: 10px;
    padding: 10px;
    border-radius: 5px;
    background: whitesmoke;
    height: 530px;
    width: 1090px;
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
