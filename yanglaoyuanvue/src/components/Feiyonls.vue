<template>
  <div class="fyls">
    <div style="background: white;padding: 10px;">
      <div style="margin: 10px 0px;">
        <el-input
          placeholder="老人姓名"
          v-model="selet.omname"
          style="width: 200px;"
          clearable>
        </el-input>
        <el-select v-model="selet.fylb" clearable placeholder="类别名称">
          <el-option
            v-for="i in fylbs"
            :key="i.v"
            :label="i.lb"
            :value="i.v">
          </el-option>
        </el-select>
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
          :data="costs"
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
            label="类别名称"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              {{scope.row.cosCategory}}
            </template>
          </el-table-column>
          <el-table-column
            label="金额（元）"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              <i class="fa fa-cny"></i>
              {{scope.row.cosMoney}}
            </template>
          </el-table-column>
          <el-table-column
            label="收费员"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              <el-tag>{{scope.row.userByUid.uname}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="费用时间"
            header-align="center"
            align="center"
            width="200">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              {{scope.row.cosDate}}
            </template>
          </el-table-column>
          <el-table-column
            label="费用说明"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              {{scope.row.cosExplain}}
            </template>
          </el-table-column>
          <el-table-column
            label="备注">
            <template slot-scope="scope">
              {{scope.row.cosRemarks}}
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
      data() {
        return {
          current:1,
          pageSize:5,
          total:0,
          http:'http://localhost:8089/',
          costs:[],
          zmoney:0,
          selet:{
            omname:null,
            fylb:null,
            dgdate: null,
          },
          fylbs:[
            {
              v:'交费',
              lb:'交费'
            },
            {
              v:'月消费',
              lb:'月消费'
            },
            {
              v:'床位费',
              lb:'床位费'
            },
            {
              v:'护理费',
              lb:'护理费'
            },
            {
              v:'餐饮费',
              lb:'餐饮费'
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
      methods: {
        cz(){
          let s = {
            omname:null,
            fylb:null,
            dgdate: null,
          }
          this.selet = s;
        },
        select(){
          let s = {};
          if(this.selet.dgdate!=null){
            s = {
              name: this.selet.omname,
              fylb: this.selet.fylb,
              kdate: this.selet.dgdate[0],
              jdate: this.selet.dgdate[1],
              no:1,
              size:this.pageSize
            }
          }else{
            s = {
              name: this.selet.omname,
              fylb: this.selet.fylb,
              kdate: '',
              jdate: '',
              no:1,
              size:this.pageSize
            }
          }
          console.log(s);
          let ppp = this.$qs.stringify(s);
          this.$axios.post('costflow/pagers',ppp)
            .then(r=>{
              console.log("cost:",r.list);
              this.costs = r.list;
              let m = 0;
              this.costs.forEach((v, i) => {
                m += v.cosMoney
              })
              this.zmoney = m;
              this.total = r.total;
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
              fylb: this.selet.fylb,
              kdate: this.selet.dgdate[0],
              jdate: this.selet.dgdate[1],
              no:this.current,
              size:this.pageSize
            }
          }else{
            s = {
              name: this.selet.omname,
              fylb: this.selet.fylb,
              kdate: '',
              jdate: '',
              no:this.current,
              size:this.pageSize
            }
          }
          console.log(s);
          let ppp = this.$qs.stringify(s);
          this.$axios.post('costflow/pagers',ppp)
            .then(r=>{
              console.log("cost:",r.list);
              this.costs = r.list;
              let m = 0;
              this.costs.forEach((v, i) => {
                m += v.cosMoney
              })
              this.zmoney = m;
              this.total = r.total;
            })
            .catch(e=>{

            })
        },
        getcost(){
          let param={
            no:this.current,
            size:this.pageSize
          };
          let ppp = this.$qs.stringify(param);
          this.$axios.post('costflow/pager',ppp)
            .then(r=>{
              console.log("cost:",r.list);
              this.costs = r.list;
              let m = 0;
              this.costs.forEach((v, i) => {
                m += v.cosMoney
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
          if(this.selet.omname!=null || this.selet.fylb!=null || this.selet.dgdate!=null || this.selet.fylb!=""){
            this.selects();
          }else{
            this.select();
          }
        },
        /* pageSize 改变时会触发*/
        handleSizeChange(pagesize){
          this.pageSize=pagesize;
          if(this.selet.omname!=null || this.selet.fylb!=null || this.selet.dgdate!=null){
            this.selects();
          }else{
            this.select();
          }
        }
      },
      mounted() {
        this.getcost();
      }
    }
</script>

<style>
  .fyls{
    margin: 10px;
    padding: 10px;
    border-radius: 5px;
    background: whitesmoke;
    height: 530px;
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
