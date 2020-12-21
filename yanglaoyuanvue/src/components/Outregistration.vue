<template>
  <div class="outregistration">
    <div>
      <el-row style="background: white;border-radius: 5px;margin: 10px;padding: 10px">
        <el-col style="font-family: 微软雅黑;">
          <el-tag type="success" style="font-size: 14px;">用户登记外出/返回</el-tag>
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
        </el-col>
      </el-row>
      <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);padding: 10px;border-radius: 5px;margin-top: 10px">
        <el-table
          :data="outregistrationPage"
          border
          style="width: 100%;border-radius: 5px">
          <el-table-column
            prop="date"
            label="老人姓名">
          </el-table-column>
          <el-table-column
            prop="name"
            label="床位号">
          </el-table-column>
          <el-table-column
            prop="address"
            label="陪同人姓名">
          </el-table-column>
          <el-table-column
            prop="address"
            label="陪同人电话">
          </el-table-column>
          <el-table-column
            prop="address"
            label="外出时间">
          </el-table-column>
          <el-table-column
            prop="address"
            label="预计返回时间">
          </el-table-column>
          <el-table-column
            prop="address"
            label="实际返回时间">
          </el-table-column>
          <el-table-column
            prop="address"
            label="外出天数">
          </el-table-column>
          <el-table-column
            prop="address"
            label="是否返回">
          </el-table-column>
          <el-table-column
            prop="address"
            label="办理人">
          </el-table-column>
          <el-table-column
            label="操作">
            <el-button type="warning" size="mini" plain>延期</el-button>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="current"
          :page-sizes="[4, 6, 8, 10]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          style="margin-top: 10px">
        </el-pagination>
      </el-row>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Outregistration",
    data(){
      return{
        total:0,
        pageSize:4,
        current:1,
        outregistrationPage:[],
        restaurants: [],
        state: '',
        timeout:  null,
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
      selectByPage(){
        this.$axios.post("outregistration/page",this.$qs.stringify({
          no:this.current,
          size:this.pageSize,
          tomName:this.selectOutregistration.tomName,
          outtrue:this.selectOutregistration.outtrue,
          time:this.selectOutregistration.time
        }))
        .then(r=>{
          this.total=r.total;
        })
      },
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
        }, 3000 * Math.random());
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
</style>

