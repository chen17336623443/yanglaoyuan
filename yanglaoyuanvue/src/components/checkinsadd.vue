<template>
  <div class="div">
    <el-tabs v-model="activeName" @tab-click="handleClick" :before-leave="beforeLeave">
      <el-tab-pane label=">" name="second">
        <span slot="label">
          <span class="span-box">
            <i class="el-icon-magic-stick" style="font-size:20px"></i>
            <span>基本信息登记</span>
            <i class="el-icon-arrow-right"></i>
          </span>
        </span>
        <!--内容 -->

        <el-card shadow="hover">
          <table
            class="tables"
            width="100%"
            bgcolor="white"
            style="border-collapse: collapse;border: 3px solid  #f5f7fa !important;margin-top: 20px;   "
          >
            <tr>
              <th
                class="biaoti"
                colspan="6"
                style="text-align: left;background-color: #f5f7fa !important;padding: 5px 0; font-size:15px"
              >基本信息</th>
            </tr>
            <tr>
              <td class="biaoti">姓名：</td>
              <td class="wenben">
                <el-input size="mini" v-model="oldman.tomName"></el-input>
              </td>
              <td class="biaoti">身份证号：</td>
              <td class="wenben">
                <el-input size="mini" v-model="oldman.tomCard"></el-input>
              </td>
              <td rowspan="4" class="biaoti">证件照-</td>
              <td rowspan="4" style="align:center">
                <img
                  height="120px"
                  src="../assets/laoren.jpg"
                  class="user-avator"
                  alt
                  style="position:relative;left:17px;"
                />
              </td>
            </tr>
            <tr>
              <td class="biaoti">性别</td>
              <td class="wenben">
                <template>
                  <el-radio v-model="oldman.tomSex" label="男">男</el-radio>
                  <el-radio v-model="oldman.tomSex" label="女">女</el-radio>
                </template>
              </td>
              <td class="biaoti">联系电话</td>
              <td class="wenben">
                <el-input size="mini" v-model="oldman.tomNumber"></el-input>
              </td>
            </tr>
            <tr>
              <td class="biaoti">家庭住址</td>
              <td class="wenben">
                <el-cascader
                  size="mini"
                  :options="options"
                  v-model="oldman.tomAddress"
                  @change="handleChange"
                ></el-cascader>
              </td>
              <td class="biaoti">人员类型</td>
              <td class="wenben">
                <el-select
                  v-model="oldman.tomCategory"
                  size="mini"
                  placeholder="人员类型"
                  style="float: left;"
                >
                  <el-option label="低保老人" value="低保老人"></el-option>
                  <el-option label="普通老人" value="普通老人 "></el-option>
                  <el-option label="孤寡老人" value="孤寡老人 "></el-option>
                  <el-option label="空巢老人" value="空巢老人 "></el-option>
                  <el-option label="五保老人" value="五保老人 "></el-option>
                  <el-option label="一般老人" value="一般老人 "></el-option>

                  <el-option label="其他" value="其他"></el-option>
                </el-select>
              </td>
            </tr>
            <tr>
              <td class="biaoti">饮食特点：</td>
              <td class="wenben">
                <el-input size="mini" v-model="oldman.tomDiet"></el-input>
              </td>
              <td class="biaoti">劳动能力</td>
              <td class="wenben">
                <el-select
                  v-model="oldman.tomLabour"
                  size="mini"
                  placeholder="劳动能力"
                  style="float: left;"
                >
                  <el-option label="有劳动能力" value="有劳动能力"></el-option>
                  <el-option label="部分劳动能力丧失" value="部分劳动能力丧失"></el-option>

                  <el-option label="其他" value="其他"></el-option>
                </el-select>
              </td>
            </tr>
            <tr>
              <td class="biaoti">登记入住日期</td>
              <td class="wenben">
                <el-date-picker
                  size="mini"
                  style="width:195px"
                  v-model="checkins.checkinbegin"
                  type="datetime"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期时间"
                ></el-date-picker>
              </td>
              <td class="biaoti">合同到期日期</td>
              <td class="wenben">
                <el-date-picker
                  size="mini"
                  style="width:195px"
                  v-model="checkins.checkinend"
                  type="datetime"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期时间"
                ></el-date-picker>
              </td>
              <td class="biaoti"></td>
              <td class="wenben"></td>
            </tr>
          </table>
          <div style="text-align: center; margin-top:20px">
            <el-button size="mini" icon="el-icon-close" @click>取 消</el-button>
            <el-button
              type="warning"
              size="mini"
              icon="el-icon-check"
              @click="oldman.tomAddress=oldman.tomAddress+'';addoldman()"
            >新增</el-button>
          </div>
        </el-card>
      </el-tab-pane>

      <el-tab-pane label="家属信息登记>" name="first">
        <span slot="label">
          <span class="span-box">
            <i class="el-icon-s-shop" style="font-size:20px"></i>
            <span>家属信息登记</span>
            <i class="el-icon-arrow-right"></i>
          </span>
        </span>

        <!-- 内容 -->

        <el-card shadow="hover">
          <el-form
            label-width="140px"
            inline
            :model="relation"
            :rules="rules"
            ref="relation"
            class="demo-ruleForm"
          >
            <el-row>
              <el-col></el-col>
            </el-row>

            <el-form-item prop="rname" label="姓名">
              <el-input size="mini" v-model="relation.rname"></el-input>
            </el-form-item>

            <el-form-item prop="card" label="身份证">
              <el-input size="mini" v-model="relation.card"></el-input>
            </el-form-item>

            <el-form-item prop="omsex" label="性别">
              <template>
                <el-radio v-model="relation.omsex" label="男">男</el-radio>
                <el-radio v-model="relation.omsex" label="女">女</el-radio>
              </template>
            </el-form-item>

            <el-form-item prop="phone" label="联系电话">
              <el-input size="mini" v-model="relation.phone"></el-input>
            </el-form-item>

            <el-form-item prop="site" label="家庭地址">
              <el-cascader
                size="mini"
                :options="options"
                v-model="relation.site"
                @change="handleChange"
              ></el-cascader>
            </el-form-item>
            <el-form-item prop="guardian" label="是否为监护人">
              <template>
                <el-radio v-model="relation.guardian" label="是">是</el-radio>
                <el-radio v-model="relation.guardian" label="否">否</el-radio>
              </template>
            </el-form-item>
            <el-form-item label="登记人">
              <el-input size="mini" v-model="relation.userByUid.uname" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item prop="relation" label="与老人关系">
              <el-select
                v-model="relation.relation"
                size="mini"
                placeholder="与老人关系"
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

            <el-row>
              <el-col :span="24">
                <div style="display: inline;">
                  <el-button size="mini" icon="el-icon-close" @click="resetForm('relation')">重置</el-button>
                  <el-button
                    type="warning"
                    size="mini"
                    icon="el-icon-check"
                    @click="relation.site=relation.site+'';submitForm('relation')"
                  >{{xg?'修改':'新增'}}</el-button>
                </div>
              </el-col>
            </el-row>
          </el-form>
        </el-card>

        <el-card>
          <el-table
            :data="relations.list"
            style="width: 100%;font-size:12px"
            row-key="id"
            border
            lazy
            :tree-props="{menus: 'menus', hasChildren: 'hasChildren'}"
          >
            <el-table-column prop="rid" label="编号" width="60"></el-table-column>

            <el-table-column prop="rname" label="姓名"></el-table-column>

            <el-table-column prop="card" label="身份证号"></el-table-column>
            <el-table-column prop="omsex" label="性别"></el-table-column>
            <el-table-column prop="phone" label="联系电话"></el-table-column>
            <el-table-column prop="site" label="家庭住址"></el-table-column>
            <el-table-column prop="relation" label="与老人关系"></el-table-column>
            <el-table-column prop="guardian" label="是否监护人"></el-table-column>
            <el-table-column prop="userByUid.uname" label="登记人"></el-table-column>

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
                  @click="deletebyrid(scope.row.rid)"
                ></el-button>
              </template>
            </el-table-column>
          </el-table>

          <el-row>
            <el-col :span="24">
              <div class="block">
                <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="relations.pageNum"
                  :page-sizes="[1, 5, 10, 50]"
                  :page-size="relations.pageSize"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="relations.total"
                ></el-pagination>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label name="third">
        <span slot="label">
          <span class="span-box">
            <i class="el-icon-s-shop" style="font-size:20px"></i>
            <span>健康信息登记</span>
            <i class="el-icon-arrow-right"></i>
          </span>
        </span>
        <!-- 健康信内容下方 -->

        <el-card shadow="hover">
          <el-form
            label-width="140px"
            inline
            :model="health"
            :rules="healthyanzheng"
            ref="health"
            class="demo-ruleForm"
          >
            <el-row>
              <el-col></el-col>
            </el-row>

            <el-form-item prop="capacity" label="能力评估等级">
              <el-select
                v-model="health.capacity"
                size="mini"
                placeholder="能力评估等级"
                style="float: left;"
              >
                <el-option label="优秀" value="优秀"></el-option>
                <el-option label="良好" value="良好"></el-option>
                <el-option label="一般" value="一般"></el-option>
                <el-option label="差" value="差"></el-option>
                <el-option label="极差" value="极差"></el-option>
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item prop="accommodation" label="自理情况">
              <el-select
                v-model="health.accommodation"
                size="mini"
                placeholder="自理情况"
                style="float: left;"
              >
                <el-option label="介助" value="介助"></el-option>
                <el-option label="介护" value="介护"></el-option>
                
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="过敏药物">
              <el-input size="mini" v-model="health.allergy"></el-input>
            </el-form-item>

            <el-form-item prop="vision" label="视力">
              <el-select
                v-model="health.vision"
                size="mini"
                placeholder="视力"
                style="float: left;"
              >
                <el-option label="正常" value="正常"></el-option>
                <el-option label="有损" value="有损"></el-option>
                <el-option label="失明" value="失明"></el-option>
                
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item prop="hearing" label="听力">
              <el-select
                v-model="health.hearing"
                size="mini"
                placeholder="视力情况"
                style="float: left;"
              >
                <el-option label="正常" value="正常"></el-option>
                <el-option label="轻度听力障碍" value="轻度听力障碍"></el-option>
                <el-option label="重度听力障碍" value="重度听力障碍"></el-option>
               
                <el-option label="其他" value="其他"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="登记人">
              <el-input size="mini" v-model="relation.userByUid.uname" :disabled="true"></el-input>
            </el-form-item>

            <el-row>
              <el-col :span="24">
              
                <el-form-item label="病史" style>
                  <el-input
                    type="textarea"
                    v-model="health.medical"
                    style="display: inline-block;width:820px; "
                  ></el-input>
                </el-form-item>
              </el-col>

              <el-col :span="24">
                <el-form-item label="主要疾病">
                  <el-checkbox-group v-model="jibings">
                    <el-checkbox
                      :key="item.tcId"
                      v-for="item in taboocrowds"
                      :label="item.tcId"
                      name="type"
                    >{{item.tcName}}</el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <div style="display: inline;">
                  <el-button size="mini" icon="el-icon-close" @click="resetForm('relation')">重置</el-button>
                  <el-button
                    type="warning"
                    size="mini"
                    icon="el-icon-check"
                    @click="submithelath('health')"
                  >新增</el-button>
                </div>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </el-tab-pane>

      <el-tab-pane label name="fourth">
        <span slot="label">
          <span class="span-box">
            <i class="el-icon-s-shop" style="font-size:20px"></i>
            <span>护理任务设置</span>
            <i class="el-icon-arrow-right"></i>
          </span>
        </span>
      </el-tab-pane>

      <el-tab-pane label name="canyin">
        <span slot="label">
          <span class="span-box">
            <i class="el-icon-s-shop" style="font-size:20px"></i>
            <span>餐饮床位选择</span>
            <i class="el-icon-arrow-right"></i>
          </span>
        </span>
      </el-tab-pane>

      <el-tab-pane label name="caiwu">
        <span slot="label">
          <span class="span-box">
            <i class="el-icon-s-shop" style="font-size:20px"></i>
            <span>入院缴费</span>
          </span>
        </span>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import { regionData, CodeToText } from "element-china-area-data";
export default {
  data() {
    return {
      relations: [],
      options: regionData,
      oldman: {
        omId: "",
        tomName: "",
        tomCard: "",
        tomSex: "",
        tomNumber: "",
        tomAddress: "",
        tomCategory: "",
        tomDiet: "",
        tomLabour: "",

        tomImg: "",
      },
      checkins: {
        accomplish: "否",
        checkintype: "未入住",
        checkinbegin: "",
        checkinend: "",

        myoldman: {
          omId: "",
        },
      },
      activeName: "second",
      pageSize: 5,
      pageNum: 1,
      // 家属信息
      relation: {
        rname: "",
        phone: "",
        card: "",
        relation: "",
        omsex: "",
        site: "",
        guardian: "",
        userByUid: { uname: "" },
        oldmanByOmId: {
          omId: "",
        },
      },

      rules: {
        rname: [
          { required: true, message: "请输入家属姓名", trigger: "blur" },
          {
            min: 2,
            max: 9,
            message: "长度在 3 到 9 个字  符",
            trigger: "blur",
          },
        ],
        phone: [
          { required: true, message: "请输入手机号码", trigger: "blur" },
          {
            min: 11,
            max: 11,
            message: "请输入正确手机号码（11位）",
            trigger: "blur",
          },
        ],
        card: [
          { required: true, message: "请输入身份证号码", trigger: "blur" },
          {
            min: 18,
            max: 18,
            message: "请输入正确身份证号码（18位）",
            trigger: "blur",
          },
        ],
        relation: [
          {
            required: true,
            message: "请选择你们之间的关系",
            trigger: "change",
          },
        ],
        site: [
          {
            required: true,
            message: "请选择你们之间的关系",
            trigger: "change",
          },
        ],
        date1: [
          {
            type: "date",
            required: true,
            message: "请选择日期",
            trigger: "change",
          },
        ],
        date2: [
          {
            type: "date",
            required: true,
            message: "请选择时间",
            trigger: "change",
          },
        ],
        type: [
          {
            type: "array",
            required: true,
            message: "请至少选择一个活动性质",
            trigger: "change",
          },
        ],
        omsex: [{ required: true, message: "请选择性别", trigger: "change" }],
        guardian: [
          { required: true, message: "请选择性别", trigger: "change" },
        ],
        desc: [{ required: true, message: "请填写活动形式", trigger: "blur" }],
      },
      // 健康信息验证
            healthyanzheng: {
       
       
        capacity: [
          {
            required: true,
            message: "请选择你们之间的关系",
            trigger: "change",
          },
        ],
        accommodation: [
          {
            required: true,
            message: "请选择你们之间的关系",
            trigger: "change",
          },
        ],
      vision: [
          {
            required: true,
            message: "请选择你们之间的关系",
            trigger: "change",
          },
        ],
         hearing: [
          {
            required: true,
            message: "请选择你们之间的关系",
            trigger: "change",
          },
        ],
     
       
      },
      xg: false,
      taboocrowds: [],
      health:{
          hid:'',
          capacity:'',
          accommodation:'',
          vision:'',
          hearing:'',
          medical:'',
          userByUid:{
            uname:''
          },
          Oldman:{
            
          },
         
          allergy:''
      }
      ,
      jibings:[]
    };
  },
  created() {
    this.relationall();
    this.taboocrowd();
  },
  methods: {
    // 查询所有的禁忌
    taboocrowd() {
      this.$axios
        .get("http://localhost:8089/food/tabAll")
        .then((res) => {
          this.taboocrowds = res;
        })

        .catch((e) => {
          console.log("查询所有禁忌报错了", e);
        });
    },
    // 修改家属信息
    xiugai(zhi) {
      this.relation = JSON.parse(JSON.stringify(zhi));
      this.relation.oldmanByOmId = { omId: zhi.oldmanByOmId.omId };
      console.log("修改的信息", this.relation);
      this.xg = true;
    },

    // 删除家属信息
    deletebyrid(id) {
      this.$axios
        .get("http://localhost:8089/relation/delete?id=" + id)
        .then((res) => {
          this.$message.success("删除成功");
          this.relationall();
        })
        .catch((e) => {
          console.log("删除家属信息的时候报错了", e);
        });
    },
    // 新增家属信息
    submitForm(formName) {
      this.relation.site = this.relation.site + "";

      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (!this.xg) {
            this.relation.oldmanByOmId.omId = this.oldman.omId;
            this.$axios
              .post("http://localhost:8089/relation/addrelation", this.relation)
              .then((res) => {
                this.$message.success("新增成功！");
                this.relationall();
              })
              .catch((e) => {
                console.log("新增家属信息报错了");
              });
          } else {
            this.$axios
              .post("http://localhost:8089/relation/update", this.relation)
              .then((res) => {
                this.$message.success("修改成功！");

                this.xg = false;
                this.relation = {
                  rname: "",
                  phone: "",
                  card: "",
                  relation: "",
                  omsex: "",
                  site: "",
                  guardian: "",
                  userByUid: { uname: "" },
                  oldmanByOmId: {
                    omId: "",
                  },
                };
                this.resetForm("relation");
                this.relationall();
              })
              .catch((e) => {
                console.log("修改家属信息报错了");
              });
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    // 新增健康信息
        submithelath(formName) {
    

      this.$refs[formName].validate((valid) => {
        if (valid) {
            this.$axios
              .post("http://localhost:8089/health/save", this.health)
              .then((res) => {
                this.$message.success("新增成功！");
                this.relationall();
              })
              .catch((e) => {
                console.log("新增家属信息报错了");
              });
         
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    relationall() {
      this.relation.userByUid.uname = localStorage.getItem("uname");
      this.$axios
        .get(
          "http://localhost:8089/relation/selectbyomid?omid=" +
            this.oldman.omId +
            "&no=" +
            this.pageNum +
            "&size=" +
            this.pageSize
        )
        .then((res) => {
          this.relations = res;
        })

        .catch((e) => {
          console.log("查询老人的的家属信息报错了");
        });
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.relationall();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      console.log(`当前页: ${val}`);
      this.relationall();
    },

    // 添加老人
    addoldman() {
      this.$axios
        .post("http://localhost:8089/oldman/insertoldman", this.oldman)
        .then((res) => {
          this.oldman = res;
          console.log("新增成功之后的老人信息", res);
          if (res != null && res != "") {
            this.checkins.myoldman.omId = res.omId;
            setTimeout((ss) => {
              this.$axios
                .post(
                  "http://localhost:8089/checkins/insertcheckins",
                  this.checkins
                )
                .then((item) => {
                  this.$message.success("新增成功！");
                  this.activeName = "first";
                  //           this.oldman={
                  //   tomName:'',
                  //   tomCard:'',
                  //   tomSex:'',
                  //   tomNumber:'',
                  //   tomAddress:'',
                  //   tomCategory:'',
                  //   tomDiet:'',
                  //   tomLabour:'',

                  //   tomImg:''
                  // };

                  //           this.checkins={
                  //   accomplish:'否',
                  //   checkintype:'未入住',
                  //   checkinbegin:'',
                  //   checkinend:'',

                  //   myoldman:{
                  //     omId:''
                  //   }

                  // };
                });
            }, 500);
          }
        })
        .catch((e) => {
          console.log("新增老人时报错了", e);
        });
    },

    handleChange(value) {
      let data =
        CodeToText[value[0]] +
        ", " +
        CodeToText[value[1]] +
        ", " +
        CodeToText[value[2]];
    },

    beforeLeave(item) {
      var p = new Promise((resolve, reject) => {
        this.$confirm("离开将不保存本页修改", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            // 你可以在这儿做些操作
            resolve();
          })
          .catch((err) => {
            // 你可以在这儿做些操作
            reject(err);
          });
      });
      return p;
    },

    handleClick(tab, event) {
      // console.log(tab, event);
    },
  },
};
</script>
<style >
.div {
  margin: 0;
  padding: 20px;
  width: 1084px;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.tables tr td {
  font-size: 12px;
  padding: 12px 5px;

  border: 1px solid #ebeef5;
}

.biaoti {
  width: 14%;
  text-align: right;
  background-color: #f5f7fa !important;
}
.wenben {
  width: 22%;
}
</style>


