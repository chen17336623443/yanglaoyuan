<template>
  <div class="div">
    <el-row :gutter="20" class="goodsindex-queryInfo" v-show="show" style="margin-bottom:30px">
      <el-col :xs="8" :sm="6" :md="6" :lg="4" :xl="4">
        <el-input
          class="goodsindex-queryInfo-li"
          v-model="juese.name"
          @keyup.enter.native="pageNum=0; shuju()"
          clearable
          size="mini"
          placeholder="输入老人名称"
        ></el-input>
      </el-col>

      <el-col :span="9.5">
        <div class="block" style="font-size: 12px; ">
          
          <el-date-picker
            @change="pageNum=1;bukeyiweikong();"
            size="mini"
			style="width:280px"
            value-format="yyyy-MM-dd"
            v-model="juese.hiredate"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="至"
            start-placeholder="登记开始日期"
            end-placeholder="登记结束日期"
            align="right"
          ></el-date-picker>
        </div>
      </el-col>
      <el-col :span="4">
         <el-select
                    v-model="checkins.accomplish"
                    size="mini"
                    placeholder="是否完成登记"
                    style="float: left;"
                    @change="pageNum=1;shuju()"
                  >
                    <el-option label="已完成" value="已完成"></el-option>
                    <el-option label="否" value="否"></el-option>
                  
                    <el-option label="其他" value="其他"></el-option>
                    <el-option label="" value=""></el-option>
                  </el-select>
      </el-col>
       <el-col :span="4">
         <el-select
                    v-model="checkins.checkintype"
                    size="mini"
                    placeholder="入住状态"
                    style="float: left;"
                     @change="pageNum=1;shuju()"
                  >
                    <el-option label="入住" value="入住"></el-option>
                    <el-option label="未入住" value="未入住"></el-option>
                  
                    <el-option label="其他" value="其他"></el-option>
                    <el-option label="" value=""></el-option>
                  </el-select>
      </el-col>

      <el-col :xs="6" :sm="4" :md="3" :lg="2" :xl="2">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="pageNum=1; shuju()">搜索</el-button>
      </el-col>
      <el-col :xs="6" :sm="4" :md="3" :lg="2" :xl="2">
        <el-button type="warning" class="goodsindex-queryInfo-li" size="mini" @click="chongzhi()">重置</el-button>
      </el-col>
    </el-row>

    <el-row style="margin-top: 10px;">
      <el-col :span="24">
        <el-row>
          <el-col :span="2">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="outerVisible = true;update=false;shuaxin();"
            >新增</el-button>
          </el-col>
          <el-col :span="3">
            <el-button
              type="warning"
              icon="el-icon-download"
              class="goodsindex-queryInfo-li"
              size="mini"
            >导出</el-button>
          </el-col>

          <el-col :span="18">
            <el-button-group style="float: right;">
              <el-button type="primary" icon="el-icon-search" @click="show=!show" size="mini"></el-button>
              <el-button type="primary" icon="el-icon-refresh" size="mini"></el-button>
              <el-button type="primary" icon="el-icon-s-operation" size="mini"></el-button>
            </el-button-group>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <!--  左侧内容-->
    <el-row :gutter="8">
      <el-col :span="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>入住登记</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <el-row>
            <el-col :span="24">
              <el-table
                :data="tableData1.list"
                style="width: 100%;font-size:12px"
                row-key="id"
                border
                lazy
                :load="load"
                :tree-props="{menus: 'menus', hasChildren: 'hasChildren'}"
              >
                <el-table-column prop="checkinid" label="编号" width="60"></el-table-column>

                <el-table-column prop="myoldman.tomName" label="老人姓名"></el-table-column>

                <el-table-column prop="myoldman.tomSex" label="性别"></el-table-column>
                <el-table-column prop="myoldman.tomCard" label="身份证"></el-table-column>
                <el-table-column prop="accomplish" label="是否完成登记"></el-table-column>
                <el-table-column prop="checkintype" label="入住状态"></el-table-column>
                <el-table-column prop="checkinbegin" label="入住开始时间"></el-table-column>
                 <el-table-column prop="checkinnew" label="登记时间"></el-table-column>
                  <el-table-column prop="checkinend" label="合同到期时间"></el-table-column>
                  
                <el-table-column prop label="操作">
                  <template slot-scope="scope">
				
                    <el-button
                      type="primary"
                      icon="el-icon-edit"
                      size="mini"
                      circle
                      @click="xiugai(scope.row);outerVisible=true;update=true;"
                    ></el-button>
                    <el-button
                      type="danger"
                      icon="el-icon-delete"
                      size="mini"
                      circle
                      @click="deletebymid(scope.row.checkinid)"
                    ></el-button>
                  </template>
                </el-table-column>
              </el-table>

              <!-- <div style="margin-top: 20px">
								<el-button @click="toggleSelection([tableData[1], tableData[2]])">切换第二、第三行的选中状态</el-button>
								<el-button @click="toggleSelection()">取消选择</el-button>
              </div>-->
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <div class="block">
                <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="tableData1.pageNum"
                  :page-sizes="[1, 5, 10, 50]"
                  :page-size="tableData1.pageSize"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="tableData1.total"
                ></el-pagination>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 新增菜单 -->
    <template>
      <el-dialog width="55%"  :title="update==true?'修改咨询':'新增咨询'" :visible.sync="outerVisible">
        <el-card class="box-card">
			<div>
 <div slot="footer" class="dialog-footer" >
			<div style="text-align: center;"> <el-button size="mini" icon="el-icon-close"  @click="outerVisible = false">取 消</el-button>
          <el-button type="warning" size="mini" icon="el-icon-check" @click="insertmenu()">{{update==true?'修改':'新增'}}</el-button></div>
          <el-form :label-position="labelPosition" label-width="120px" :model="formLabelAlign">
            <el-divider content-position="left" ><span style="font-size:15px">老人信息</span></el-divider>
			
            <el-row :gutter="30" style="margin-bottom：150px;">
              <el-col :span="12">
                <el-form-item label="老人名字">
                  <el-input size="mini" v-model="consult.omname"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="身份证">
                  <el-input size="mini" v-model="consult.card"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="性别" style="float:left">
                  <template>
                    <el-radio v-model="consult.omsex" label="男">男</el-radio>
                    <el-radio v-model="consult.omsex" label="女">女</el-radio>
                  </template>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="年龄">
                  <el-input size="mini" v-model="consult.omage"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="自理情况">
                  <el-select
                    v-model="consult.omselfCare"
                    size="mini"
                    placeholder="自理情况"
                    style="float: left;"
                  >
                    <el-option label="自理" value="自理"></el-option>
                    <el-option label="介助" value="介助"></el-option>
                    <el-option label="介护" value="介护"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="家庭地址" style="float:left;">
                  <el-cascader
                    size="mini"
                    :options="options"
                    v-model="consult.site"
                    @change="handleChange"
                  ></el-cascader>
                </el-form-item>
              </el-col>
            </el-row>

            <el-divider content-position="left" > <span style="font-size:15px">咨询人信息</span></el-divider>
            <el-row :gutter="30">
              <el-col :span="12">
                <el-form-item label="咨询人名字">
                  <el-input size="mini" v-model="consult.oname"></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="12">
                <el-form-item label="与老人关系">
                  <el-select
                    v-model="consult.relation"
                    size="mini"
                    placeholder="自理情况"
                    style="float: left;"
                  >
                    <el-option label="父子" value="父子"></el-option>
                    <el-option label="父女" value="父女"></el-option>
                    <el-option label="本人" value="本人"></el-option>
                    <el-option label="母子" value="母子"></el-option>
                    <el-option label="母女" value="母女"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
			  <el-col :span="24">
 <el-col :span="12"  >
                   <el-form-item label="联系电话">
                  <el-input size="mini" v-model="consult.cphone"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="咨询日期">
                  <div class="block">
                   
                    <el-date-picker  size="mini" style="width:195px" v-model="consult.newtime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间"></el-date-picker>
                  </div>
                </el-form-item>
              </el-col>




			  </el-col>

				

			  <el-col :span="12">
                <el-form-item label="咨询方式">
                  <el-select
                    v-model="consult.way"
                    size="mini"
                    placeholder="咨询方式"
                    style="float: left;"
                  >
                    <el-option label="电话" value="电话"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>


			 


			  
			 

			  
			  <el-col :span="12">
                   <el-form-item label="接待人">
                  <el-input size="mini" v-model="consult.myuser.uname"  :disabled="true"></el-input>
                </el-form-item>
              </el-col>


			  <el-col :span="24">
                   <el-form-item label="咨询内容">
                 <el-input
				 size="mini"
  type="textarea"
  :rows="2"
  placeholder="请输入内容"
  v-model="consult.content">
</el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>

         
        </div>


			</div>
  
</el-card>
       
      </el-dialog>
    </template>
  </div>
</template>

<script>
import { regionData, CodeToText } from "element-china-area-data";
export default {
  data() {
    return {
      checkins:{
        accomplish:'',
        checkintype:''
      },
		
      options: regionData,
      update: false,

      dangemenu: {},
      labelPosition: "left",
      consult: {
        cId: "",
        omname: "",
        card: "",
        omsex: "男",
        omage: "",
        omselfCare: "",
        oname: "",
        relation: "",
        newtime: "",
        way: "",
        cphone: "",
        content: "",
        site: "",
        myuser: {
		  ename: "",
		  uid:1
        },
      },

      mymenu: {
        title: "",
        component: "",
        dengji: 0,
        mymenu: {
          title: "",
          mid: "",
        },
        icon: "",
      },

      formLabelAlign: {
        input: {
          title: "",
        },
        tubiao: "",
        name: "",
        region: "",
        type: "",
      },

      outerVisible: false,
      innerVisible: false,

      menus: [],

      tableData: [
        {
          id: 1,
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          id: 2,
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄",
        },
        {
          id: 3,
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄",
          children: [
            {
              id: 31,
              date: "2016-05-01",
              name: "王小虎",
              address: "上海市普陀区金沙江路 1519 弄",
            },
            {
              id: 32,
              date: "2016-05-01",
              name: "王小虎",
              address: "上海市普陀区金沙江路 1519 弄",
            },
          ],
        },
        {
          id: 4,
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
      tableData1: [
        {
          id: 1,
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          id: 2,
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄",
        },
        {
          id: 3,
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄",
          hasChildren: true,
        },
        {
          id: 4,
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
      filterText: "",

      defaultProps: {
        children: "menus",
        label: "title",
      },
      zhiweis: [],

      multipleSelection: [],
      show: true,
      juese: {
        name: "",
        hiredate: ["", ""],
      },
      pageNum: 1,
      pageSize: 5,

      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
    };
  },
  created() {
    // this.loadData();
    this.shuju();
    // this.menu();
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    },
  },
  methods: {
	 
	  xiugai(row){
		  this.consult=row;
		 
		 if(row.site!='' && row.site!=null){
			  this.consult.site = row.site.split(',');
		  let data = CodeToText[this.consult.site[0]] + " " + CodeToText[this.consult.site[1]] + " " + CodeToText[this.consult.site[2]];
		
		  console.log('我要的数据有',data)
		 }
	  },
    handleChange(value) {
      let data =
        CodeToText[value[0]] +
        ", " +
        CodeToText[value[1]] +
        ", " +
        CodeToText[value[2]];
    },
    chongzhi() {
      this.juese.hiredate = ["", ""];
      this.pageNum = 1;
      this.juese.name = "";
      this.shuju();
    },
    /*	<!-- 当某个菜单没有上级是要给设为'' */
    jiazhi(a) {
      a.mymenu = { title: "" };
      return a;
    },

    /* 新增刷新 */
    shuaxin() {
      this.mymenu = {
        title: "",
        component: "",
        dengji: "",
        mymenu: {
          title: "",
          mid: "",
        },
        icon: "",
      };
    },

    /* 修改菜单 */

    /* 点击修改按钮传值 */

    /* 删除某个菜单 */
    deletebymid(mid) {
      this.$axios
        .get("http://localhost:8089/checkins/delete?id=" + mid)
        .then((res) => {
          if (res != 0) {
            this.$message.success("删除成功");
          
            this.shuju();
         
          } else {
            this.$message.error("删除失败");
          }
        });
    },

    /* 新增菜单menu */
    insertmenu() {
		this.consult.site=this.consult.site+'';
      if (this.update) {
        this.$axios
          .post("http://localhost:8089/consult/updateconsult", this.consult)
          .then((res) => {
            if (res != 0) {
			  this.$message.success("修改成功");
			   this.consult={
        cId: "",
        omname: "",
        card: "",
        omsex: "男",
        omage: "",
        omselfCare: "",
        oname: "",
        relation: "",
        newtime: "",
        way: "",
        cphone: "",
        content: "",
        site: "",
        myuser: {
		  ename: "",
		  uid:1
        },
      };
            } else {
              this.$message.error("修改失败");
            }
          })
          .catch((e) => {
            console.log("在修改菜单时候报错了", e);
          });
      } else {
        this.$axios
          .post("http://localhost:8089/consult/inserconsult", this.consult)
          .then((res) => {
            if (res != 0) {
			  this.$message.success("新增成功");
			  this.consult={
        cId: "",
        omname: "",
        card: "",
        omsex: "男",
        omage: "",
        omselfCare: "",
        oname: "",
        relation: "",
        newtime: "",
        way: "",
        cphone: "",
        content: "",
        site: "",
        myuser: {
		  ename: "",
		  uid:1
        },
      };
         

              this.shuju();

             
            } else {
              this.$message.error("新增失败");
            }
          })
          .catch((e) => {
            console.log("在新增菜单时候报错了", e);
          });
      }
      this.outerVisible = false;
    },
    bukeyiweikong() {
      if (this.juese.hiredate != "" && this.juese.hiredate != null) {
        this.shuju();
      } else {
        this.juese.hiredate = ["", ""];
        this.shuju();
      }
    },

    shuju() {
		this.consult.myuser.uname= localStorage.getItem("uname");
      if (this.juese.hiredate != "" && this.juese.hiredate != null) {
        this.$axios
          .post(
            ("http://localhost:8089/checkins/all?no=" +
              this.pageNum +
              "&size=" +
              this.pageSize +
              "&tomName=" +
              this.juese.name +
              "&begin=" +
              this.juese.hiredate[0] +
              "&end=" +
              this.juese.hiredate[1]),this.checkins
          )
          .then((res) => {
			this.tableData1 = res;
			
			

            console.log("菜单数据", res);
          })
          .catch((e) => {
            console.log("获取数据菜单时候报错", e);
          });
      } else {
        this.juese.hiredate = ["", ""];
        this.$axios
          .get(
            "http://localhost:8089/consult/all?no=" +
              this.pageNum +
              "&size=" +
              this.pageSize +
              "&name=" +
              this.juese.name +
              "&begin=" +
              this.juese.hiredate[0] +
              "&end=" +
              this.juese.hiredate[1]
          )
          .then((res) => {
            this.tableData1 = res;
            console.log("菜单数据", res);
          })
          .catch((e) => {
            console.log("获取数据菜单时候报错", e);
          });
      }
    },

    load(tree, treeNode, resolve) {
      console.log("tree", tree, "resolve", resolve, "treeNode", treeNode);
      setTimeout(() => {
        resolve([
          /*  {
						    id: 31,
						    date: '2016-05-01',
						    name: '王小虎',
						    address: '上海市普陀区金沙江路 1519 弄'
						  }, {
						    id: 32,
						    date: '2016-05-01',
						    name: '王小虎',
						    address: '上海市普陀区金沙江路 1519 弄'
						  } */
        ]);
      }, 1000);
    },
    filterNode(value, data) {
      console.log("value", value);
      console.log("data", data);
      if (!value) return true;
      return data.title.indexOf(value) !== -1;
    },

    handleNodeClick(data) {},

    /* 树形控件的方法 */
    getCheckedNodes() {
      console.log(this.$refs.tree.getCheckedNodes());

      if (this.$refs.tree.getCheckedNodes().length > 1) {
        this.$message.warning("只允许一个上级菜单！");
      } else if (this.$refs.tree.getCheckedNodes().length == 1) {
        this.mymenu.mymenu = this.$refs.tree.getCheckedNodes()[0];
        this.innerVisible = false;
      } else {
        this.mymenu.mymenu = { title: "" };
        this.innerVisible = false;
      }
    },
    getCheckedKeys() {
      console.log(this.$refs.tree.getCheckedKeys());
      let caidan = this.$refs.tree.getCheckedKeys();

      this.multipleSelection.forEach((zhiwie) => {
        this.$axios
          .get(
            "http://localhost:8085/zhiweihecaidanbiao/bydeleteall?deptno=" +
              this.multipleSelection[0].mydept.deptno +
              "&positionID=" +
              zhiwie.positionId
          )
          .then((res) => {
            caidan.forEach((item, index) => {
              let zhiweihecaidanbiao = {
                mymenu: {
                  mid: item,
                },
                myempzhiwei: {
                  positionId: zhiwie.positionId,
                },
                mydept: {
                  deptno: zhiwie.mydept.deptno,
                },
              };

              setTimeout(() => {
                this.$axios
                  .post(
                    "http://localhost:8085/zhiweihecaidanbiao/add",
                    zhiweihecaidanbiao
                  )

                  .then((res) => {
                    if (caidan.length - 1 == index) {
                      this.$message.success("分配成功");
                      this.loadData();
                    }
                  })

                  .catch((e) => {
                    console.log("发生了错误信息", e);
                  });
              }, 200);
            });
          })

          .catch((e) => {
            console.log("删除数据时菜单报错", e);
          });
      });

      /* 结束 */
    },
    setCheckedNodes() {
      this.$refs.tree.setCheckedNodes([
        {
          id: 5,
          label: "二级 2-1",
        },
        {
          id: 9,
          label: "三级 1-1-1",
        },
      ]);
    },
    /* setCheckedKeys() {
			   this.$refs.tree.setCheckedKeys([3]);
			 }, */

    setCheckedKeys(shuzu) {
      if (shuzu.length == 0 || shuzu.leng > 1) {
        this.$refs.tree.setCheckedKeys([]);
      } else {
        this.$refs.tree.setCheckedKeys(shuzu);
      }
    },
    resetChecked() {
      this.$refs.tree.setCheckedKeys([]);
    },

    /* 查询出所有的菜单 */

    menu() {
      this.$axios
        .get("http://localhost:8085/menu/all")
        .then((res) => {
          this.menus = res;
          console.log("所有的菜单信息", res);
        })

        .catch((e) => {
          console.log((e) => {
            console.log("在 获取菜单信息是报错", e);
          });
        });
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.shuju();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      console.log(`当前页: ${val}`);
      this.shuju();
    },

    loadData() {
      this.$axios
        .get(
          "http://localhost:8085/zhiwei/zwall?pageNum=" +
            this.pageNum +
            "&pageSize=" +
            this.pageSize
        )
        .then((res) => {
          this.zhiweis = res;
        })
        .catch((e) => {
          console.log("在获取职位信息时发生了错误:", e);
        });
    },

    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      console.log("我要的数据", val);

      if (val.length < 2) {
        let shuzu = [];
        if (val.length == 1) {
          if (val[0].zhiweicaidanbiaos.length != 0) {
            val[0].zhiweicaidanbiaos.forEach((item) => {
              shuzu.push(item.mymenu.mid);
            });
          }
        }
        this.setCheckedKeys(shuzu);
      } else {
        this.setCheckedKeys([]);
      }
      this.multipleSelection = val;
    },
  },
};
</script>

<style>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 100%;
}

.box-card1 {
  width: 100%;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.el-dialog__body {
  padding: 5px 20px;
}
.div {
  margin: 0;
  padding: 20px;
}

</style>
