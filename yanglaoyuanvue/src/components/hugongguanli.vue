<template>
  <div class="div">
    <el-tabs type="border-card">
      <el-tab-pane label="护工信息">
        <el-form ref="form" :model="form" label-width="70px" :inline="true">
          <el-form-item label="护工名称">
            <el-input
              v-model="form.name"
              style="width: 140px"
              placeholder="请输入护工名称"
            ></el-input>
          </el-form-item>
          <el-form-item label="联系电话">
            <el-input
              v-model="form.phone"
              style="width: 140px"
              placeholder="请输入联系电话"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="success" @click="like" plain>查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              plain
              style="margin-left: 300px"
              @click="ap"
            >
              安排
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" plain @click="lz">离职</el-button>
          </el-form-item>
        </el-form>
        <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column prop="acId" label="护工编号" width="100">
          </el-table-column>
          <el-table-column prop="acName" label="护工姓名" width="120">
          </el-table-column>
          <el-table-column prop="acIdcare" label="身份证号" width="180">
          </el-table-column>
          <el-table-column prop="acPhone" label="联系方式" width="120">
          </el-table-column>
          <el-table-column label="性别" width="80">
            <template slot-scope="s">
              <span v-if="s.row.acSex == 0">女</span>
              <span v-if="s.row.acSex == 1">男</span>
            </template>
          </el-table-column>
          <el-table-column label="地址" width="140">
            <template slot-scope="s">
              <el-popover trigger="hover" placement="top">
                <p>{{ s.row.acDizi }}</p>
                <div slot="reference" class="name-wrapper">
                  {{ s.row.acDizi | handleText }}
                </div>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column label="看护老人" width="120">
            <template slot-scope="s">
              <el-popover placement="right" width="400" trigger="click">
                <el-button
                  type="text"
                  @click="dellr(s.row.acId)"
                  style="margin-left: 8px"
                >
                  清空老人
                </el-button>
                <el-table :data="olddata">
                  <el-table-column
                    width="130"
                    property="tomName"
                    label="老人名称"
                  ></el-table-column>
                  <el-table-column
                    width="130"
                    property="tomSex"
                    label="老人性别"
                  ></el-table-column>
                  <el-table-column
                    width="130"
                    property="tomNumber"
                    label="联系电话"
                  ></el-table-column>
                </el-table>
                <el-button
                  type="text"
                  @click="chakan(s.row.acId)"
                  slot="reference"
                >
                  查看老人
                </el-button>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column prop="address" label="操作" show-overflow-tooltip>
            <template slot-scope="s">
              <el-button type="text" @click="anpai(s.row)"> 安排 </el-button>
              <span>
                <el-button
                  v-if="s.row.oldmenByAcId.length != 0"
                  type="text"
                  @click="dellr(s.row.acId)"
                  >清除老人</el-button
                >
              </span>
              <span>
                <el-button
                  v-if="s.row.oldmenByAcId.length == 0"
                  type="text"
                  @click="delhg(s.row.acId)"
                  >离职</el-button
                >
              </span>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
          background
          style="float: right; margin-top: 20px"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-size="pagesize"
          layout="total, prev, pager, next"
          :total="total"
        >
        </el-pagination>
        <el-dialog title="老人安排" :visible.sync="dialogTableVisible">
          <el-card class="box-card">
            <el-form
              ref="from1"
              :model="from1"
              label-width="70px"
              :inline="true"
            >
              <fieldset style="padding:20px;border-color:#F2F6FC">
                <legend>护工信息</legend>
                <el-form-item label="护工名称">
                <el-input
                  v-model="from1.name"
                  style="width: 140px"
                  placeholder="请输入护工名称"
                  disabled
                ></el-input>
              </el-form-item>
              <el-form-item label="联系电话">
                <el-input
                  v-model="from1.phone"
                  style="width: 140px"
                  placeholder="请输入联系电话"
                  disabled
                ></el-input>
              </el-form-item>
              </fieldset>
              <fieldset style="padding:20px;margin-top:10px;border-color:#F2F6FC">
                <legend>老人选择</legend>
              <el-checkbox-group v-model="laoren" size="medium">
                <el-checkbox
                  v-for="k in kongxian"
                  :key="k.index"
                  :label="k.omId"
                  border
                  style="margin-top:10px"
                  >{{ k.tomName }}</el-checkbox
                >
              </el-checkbox-group>
              </fieldset>
              <el-form-item style="float: right; margin-top: 20px">
                <el-button type="primary" plain @click="anpailaor">
                  确认安排
                </el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </el-dialog>
      </el-tab-pane>
      <el-tab-pane label="护工新增">
        <div style="width: 1000px">
          <el-form ref="form" :model="form" label-width="80px" :inline="true">
            <el-form-item label="护工名称">
              <el-input v-model="form.acName"></el-input>
            </el-form-item>
            <el-form-item label="身份证号">
              <el-input v-model="form.acIdcare"></el-input>
            </el-form-item>
            <br />
            <el-form-item label="联系电话">
              <el-input v-model="form.acPhone"></el-input>
            </el-form-item>
            <el-form-item label="出生年月">
              <el-date-picker
                v-model="form.acBirth"
                type="date"
                placeholder="选择日期"
                style="width: 202px"
              >
              </el-date-picker>
            </el-form-item>
            <br />
            <el-form-item label="性别">
              <el-radio v-model="form.acSex" label="0">女</el-radio>
              <el-radio v-model="form.acSex" label="1">男</el-radio>
            </el-form-item>
            <br />
            <el-form-item label="家庭地址">
              <el-select
                v-model="provinceValue"
                placeholder="请选择"
                @change="selectProvimce"
              >
                <el-option
                  v-for="(item, index) of provincearr"
                  :key="index"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
              <el-select
                v-model="cityValue"
                placeholder="请选择"
                @change="selectcity"
              >
                <el-option
                  v-for="(item, index) of cityarr"
                  :key="index"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
              <el-select placeholder="请选择" v-model="RegionValue">
                <el-option
                  v-for="(item, index) of regionarr"
                  :key="index"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-form-item>
            <br />
            <el-form-item label="详情地址">
              <el-input
                type="textarea"
                v-model="form.desc"
                style="width: 665px"
                rows="3"
              ></el-input>
            </el-form-item>
            <br />
            <el-form-item style="margin-left: 80px">
              <el-button type="primary" @click="submitForm">立即创建</el-button>
              <el-button @click="resetForm">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { province } from "../assets/js/map";
export default {
  data() {
    return {
      form: {},
      laoren: [],
      currentPage: 0,
      xuanzhe: [],
      kongxian: [],
      tableData: [],
      pagesize: 0,
      pagenum: 0,
      olddata: [],
      total: 0,
      dialogTableVisible: false,
      form: {},
      province: [],
      cityarr: [],
      regionarr: [],
      provinceValue: "",
      cityValue: "",
      RegionValue: "",
      from1: {},
    };
  },
  filters: {
    handleText(value) {
      if (!value) return "";
      if (value.length > 6) {
        return value.slice(0, 6) + "...";
      }
      return value;
    },
  },
  methods: {
    //页面加载查询护工数据
    loding() {
      this.$http.get("aca/all?num=1").then((res) => {
        this.pagenum = 1;
        this.tableData = res.list;
        this.pagesize = res.pageSize;
        this.total = res.total;
        this.currentPage = res.pageNum;
      });
      this.$http.get("oldman/noall").then((res) => {
        this.kongxian = res;
        console.log(res);
      });
    },
    //根据护工id查看老人
    chakan(id) {
      this.olddata = [];
      this.$http.get("aca/byid?acid=" + id).then((res) => {
        this.olddata = res;
      });
    },
    //分页上下页跳转
    handleCurrentChange(val) {
      if (this.pagenum == 1) {
        this.$http.get("aca/all?num=" + val).then((res) => {
          this.pagenum = 1;
          this.tableData = res.list;
          this.pagesize = res.pageSize;
          this.currentPage = res.pageNum;
          this.total = res.total;
        });
      } else if (this.pagenum == 2) {
        this.$http
          .get(
            "aca/like?num=" +
              val +
              "&acname=" +
              this.form.name +
              "&acphone=" +
              this.form.phone
          )
          .then((res) => {
            this.pagenum = 2;
            this.tableData = res.list;
            this.pagesize = res.pageSize;
            this.currentPage = res.pageNum;
            this.total = res.total;
          });
      }
    },
    //复选框获取选中行数据
    handleSelectionChange(val) {
      this.xuanzhe = val;
    },
    //根据名称和电话模糊查询
    like() {
      if (
        (this.form.name == undefined && this.form.phone == undefined) ||
        (this.form.name == "" && this.form.phone == "") ||
        (this.form.name == "" && this.form.phone == undefined) ||
        (this.form.name == undefined && this.form.phone == "")
      ) {
        this.$message("未检测当前输入框的条件，默认查询全部！");
        this.loding();
      } else {
        this.$http
          .get(
            "aca/like?num=1&acname=" +
              this.form.name +
              "&acphone=" +
              this.form.phone
          )
          .then((res) => {
            this.pagenum = 2;
            this.tableData = res.list;
            this.pagesize = res.pageSize;
        this.currentPage=res.pageNum
            this.total = res.total;
          });
      }
    },
    //获取省级名称
    selectProvimce(name) {
      this.cityarr = [];
      this.regionarr = [];
      this.cityValue = "";
      this.RegionValue = "";
      for (let item of this.provincearr) {
        if (name == item.name) {
          console.log(item);
          this.cityarr = item.children;
        }
      }
    },
    //获取市级城市名
    selectcity(name) {
      this.regionarr = [];
      this.RegionValue = "";
      for (let item of this.cityarr) {
        if (name == item.name) {
          this.regionarr = item.children;
        }
      }
    },
    //重置表单数据
    resetForm() {
      this.form = {};
      this.provinceValue = "";
      this.cityValue = "";
      this.RegionValue = "";
      this.cityarr = [];
      this.regionarr = [];
    },
    //提交表单数据新增护工
    submitForm() {
      if (
        this.form.acName == undefined ||
        this.form.acIdcare == undefined ||
        this.form.acBirth == undefined ||
        this.form.acSex == undefined
      ) {
        this.$message.error("请将必填信息填写完成！！！");
      } else {
        var dizi =
          this.provinceValue +
          this.cityValue +
          this.RegionValue +
          this.form.desc;
        this.$http
          .post("aca/add", {
            acName: this.form.acName,
            acIdcare: this.form.acIdcare,
            acSex: this.form.acSex,
            acBirth: this.form.acBirth,
            acDizi: dizi,
            acPhone: this.form.acPhone,
          })
          .then((res) => {
            this.$message({
              message: res,
              type: "success",
            });
            this.resetForm();
            this.loding();
          });
      }
    },
    //清除老人
    dellr(id) {
      this.$confirm("此操作将移除该护工管理下的老人, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http.get("aca/byid?acid=" + id).then((res) => {
            res.forEach((e) => {
              this.$http.get("oldman/del?omid=" + e.omId).then((res) => {});
            });
            this.$message({
              message: "清除老人成功!",
              type: "success",
            });
            this.loding();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消离职",
          });
        });
    },
    //护工离职
    delhg(id) {
      this.olddata = [];
      this.$http.get("aca/byid?acid=" + id).then((res) => {
        this.olddata = res;
        if (this.olddata.length == 0) {
          this.$confirm("此操作将该护工离职, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          })
            .then(() => {
              this.$http.get("aca/del?acid=" + id).then((res) => {
                this.$message({
                  message: res,
                  type: "success",
                });
                this.loding();
              });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消离职",
              });
            });
        } else {
          this.$message.error("该护工下面还有老人，暂不能离职");
        }
      });
    },
    //安排老人
    anpai(row) {
      this.from1 = {};
      this.from1.id = row.acId;
      this.from1.name = row.acName;
      this.from1.phone = row.acPhone;
      this.dialogTableVisible = true;
    },
    //选择安排
    ap() {
      if (this.xuanzhe.length == 1) {
        this.xuanzhe.forEach((x) => {
          this.from1 = {};
          this.from1.id = x.acId;
          this.from1.name = x.acName;
          this.from1.phone = x.acPhone;
          this.dialogTableVisible = true;
        });
      } else {
        this.$message.error("选择护工错误！");
      }
    },
    //选中离职
    lz() {
      if (this.xuanzhe.length == 1) {
        this.xuanzhe.forEach((x) => {
          this.delhg(x.acId);
        });
      } else {
        this.$message.error("选择护工错误！");
      }
    },
    //确认老人安排
    anpailaor() {
      console.log(this.laoren);
      this.laoren.forEach((l) => {
        this.$http
          .get("oldman/upid?acid=" + this.from1.id + "&omid=" + l)
          .then((res) => {});
        console.log(l);
      });
      this.$message({
        message: "安排成功！",
        type: "success",
      });
      this.loding();
      this.from1 = {};
      this.dialogTableVisible = false;
    },
  },
  created() {
    this.loding();
    this.provincearr = province;
    this.selectProvimce(this.provinceValue);
    this.selectcity(this.cityValue);
  },
};
</script>

<style scoped>
.div {
  padding: 20px;
  margin: 0;
}
.el-table td,
.el-table th {
  text-align: center;
}
</style>
