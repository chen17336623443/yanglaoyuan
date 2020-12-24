<template>
  <div class="div">
    <el-tabs type="border-card" style="width: 1087px">
      <el-tab-pane label="护理信息">
        <el-row>
          <el-col :span="6" style="margin-left: 15px">
            <el-card class="box-card" style="height: 700px">
              <div slot="header" class="clearfix">
                <span>护理级别菜单</span>
                <el-button
                  style="float: right; padding: 3px 0"
                  type="text"
                  @click="dialogTableVisibleadd = true"
                >
                  新增级别
                </el-button>
              </div>
              <ul style="list-style: none">
                <li
                  v-for="l in jb"
                  :key="l.index"
                  class="li1"
                  @click="jibei(l)"
                >
                  <span>{{ l.tslName }}</span>
                </li>
              </ul>
            </el-card>
          </el-col>

          <el-dialog
            title="新增护理级别"
            :visible.sync="dialogTableVisibleadd"
            width="60%"
            :before-close="handleClose"
          >
            <fieldset style="padding: 20px; border-color: #f2f6fc">
              <legend>级别基本信息填写</legend>
              <el-form
                ref="formadd"
                :model="formadd"
                label-width="70px"
                :inline="true"
              >
                <el-form-item label="级别名称">
                  <el-input
                    v-model="formadd.tslName"
                    style="width: 250px"
                  ></el-input>
                </el-form-item>
                <br />
                <el-form-item label="护理对象">
                  <el-select
                    v-model="formadd.tslObject"
                    placeholder="请选择活动区域"
                    style="width: 250px"
                  >
                    <el-option label="自理" value="自理"></el-option>
                    <el-option label="介助" value="介助"></el-option>
                    <el-option label="介护" value="介护"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="级别费用">
                  <el-input
                    v-model="tslMoney"
                    style="width: 250px"
                    :readonly="true"
                  ></el-input>
                </el-form-item>
                <el-form-item label="护理备注">
                  <el-input
                    type="textarea"
                    v-model="formadd.tslOther"
                    rows="3"
                    style="width: 588px"
                    resize="none"
                  ></el-input>
                </el-form-item>
              </el-form>
            </fieldset>
            <fieldset
              style="padding: 20px; margin-top: 10px; border-color: #f2f6fc"
            >
              <legend>设置护理计划</legend>
              <el-table
                ref="multipleTable"
                :data="tableData"
                tooltip-effect="dark"
                size="mini"
                height="250"
                style="width: 720px"
                @selection-change="handleSelectionChange"
              >
                <el-table-column type="selection" width="55"> </el-table-column>

                <el-table-column label="护理名称" width="100">
                  <template slot-scope="s">
                    <el-popover trigger="hover" placement="top">
                      <p>{{ s.row.nuName }}</p>
                      <div slot="reference" class="name-wrapper">
                        {{ s.row.nuName | handleText }}
                      </div>
                    </el-popover>
                  </template>
                </el-table-column>
                <el-table-column prop="nuMonry" label="护理费用" width="80">
                </el-table-column>
                <el-table-column prop="nuWay" label="收费方式" width="80">
                </el-table-column>
                <el-table-column label="执行频次" width="100">
                  <template slot-scope="s"> {{ s.row.tsldNumber }} </template>
                </el-table-column>
                <el-table-column label="执行时间" width="100">
                  <template slot-scope="s">
                    <el-popover trigger="hover" placement="top">
                      <p>{{ s.row.tsldTime }}</p>
                      <div slot="reference" class="name-wrapper">
                        {{ s.row.tsldTime | handleText }}
                      </div>
                    </el-popover>
                  </template>
                </el-table-column>
                <el-table-column label="任务类型" width="100">
                  <template slot-scope="s"> {{ s.row.tsldType }} </template>
                </el-table-column>
                <el-table-column label="操作">
                  <template slot-scope="s">
                    <el-button type="text" size="mini" @click="xuanze(s.row)">
                      设置时间
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </fieldset>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogTableVisibleadd = false">
                取 消
              </el-button>
              <el-button
                type="primary"
                @click="addqd"
                v-loading.fullscreen.lock="fullscreenLoading"
              >
                确 定
              </el-button>
            </span>
            <el-dialog
              width="40%"
              title="任务设置"
              :visible.sync="innerVisible"
              append-to-body
            >
              <el-card class="box-card">
                <el-form label-width="80px">
                  <el-divider content-position="left"
                    >请设置护理项目类型</el-divider
                  >
                  <el-form-item label="执行频率">
                    <el-radio-group v-model="pinci" @change="gaibian">
                      <el-radio label="周任务"></el-radio>
                      <el-radio label="天任务"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="任务类型">
                    <el-select v-model="leibei" placeholder="请选择活动区域">
                      <el-option label="一般任务" value="一般任务"></el-option>
                      <el-option label="关键任务" value="关键任务"></el-option>
                    </el-select>
                  </el-form-item>
                </el-form>
                <el-divider content-position="left">请选择时间</el-divider>
                <div v-show="a1">
                  <el-checkbox-group
                    v-model="checkList"
                    style="margin-left: 20px"
                  >
                    <el-checkbox label="周一"></el-checkbox>
                    <el-checkbox label="周二"></el-checkbox>
                    <el-checkbox label="周三"></el-checkbox>
                    <el-checkbox label="周四"></el-checkbox>
                    <el-checkbox label="周五"></el-checkbox>
                    <el-checkbox
                      label="周六"
                      style="margin-top: 10px"
                    ></el-checkbox>
                    <el-checkbox label="周日"></el-checkbox>
                  </el-checkbox-group>
                </div>
                <div v-show="a2">
                  <el-radio-group v-model="time">
                    <el-radio label="06:00"></el-radio>
                    <el-radio label="07:00"></el-radio>
                    <el-radio label="08:00"></el-radio>
                    <el-radio label="09:00"></el-radio>
                    <el-radio label="10:00"></el-radio>
                    <el-radio label="11:00" style="margin-top: 10px"></el-radio>
                    <el-radio label="12:00"></el-radio>
                    <el-radio label="13:00"></el-radio>
                    <el-radio label="14:00"></el-radio>
                    <el-radio label="15:00"></el-radio>
                    <el-radio label="16:00" style="margin-top: 10px"></el-radio>
                    <el-radio label="17:00"></el-radio>
                    <el-radio label="18:00"></el-radio>
                    <el-radio label="19:00"></el-radio>
                    <el-radio label="20:00"></el-radio>
                  </el-radio-group>
                </div>
              </el-card>
              <span slot="footer" class="dialog-footer">
                <el-button
                  @click="
                    (innerVisible = false), (dialogTableVisibleadd = true)
                  "
                >
                  取 消
                </el-button>
                <el-button type="primary" @click="addsss"> 确 定 </el-button>
              </span>
            </el-dialog>
          </el-dialog>

          <el-col :span="17" style="margin-left: 15px">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>基本信息</span>
                <el-button
                  style="float: right; padding: 3px 0"
                  type="text"
                  @click="xiugaijb"
                >
                  修改
                </el-button>
              </div>

              <el-form
                ref="form"
                :model="form"
                label-width="70px"
                :inline="true"
              >
                <el-form-item label="级别名称">
                  <el-input
                    v-model="form.tslName"
                    style="width: 250px"
                    :readonly="true"
                  ></el-input>
                </el-form-item>
                <br />
                <el-form-item label="护理对象">
                  <el-input
                    v-model="form.tslObject"
                    style="width: 250px"
                    :readonly="true"
                  ></el-input>
                </el-form-item>
                <el-form-item label="护理费用">
                  <el-input
                    v-model="form.tslMoney"
                    style="width: 250px"
                    :readonly="true"
                  ></el-input>
                </el-form-item>
                <el-form-item label="护理备注">
                  <el-input
                    type="textarea"
                    v-model="form.tslOther"
                    rows="3"
                    style="width: 588px"
                    :readonly="true"
                    resize="none"
                  ></el-input>
                </el-form-item>
              </el-form>
            </el-card>

            <el-card class="box-card" style="margin-top: 5px">
              <div slot="header" class="clearfix">
                <span>级别项目</span>
              </div>
              <el-table
                :data="Thenursingleveldetails"
                size="mini"
                height="250"
                style="width: 720px"
              >
                <el-table-column label="护理名称" width="100">
                  <template slot-scope="s">
                    <el-popover trigger="hover" placement="top">
                      <p>{{ s.row.nursingByNuId.nuName }}</p>
                      <div slot="reference" class="name-wrapper">
                        {{ s.row.nursingByNuId.nuName | handleText }}
                      </div>
                    </el-popover>
                  </template>
                </el-table-column>
                <el-table-column prop="nursingByNuId.nuMonry" label="护理费用" width="120">
                </el-table-column>
                <el-table-column prop="nursingByNuId.nuWay" label="收费方式" width="120">
                </el-table-column>
                <el-table-column label="执行频次" width="120">
                  <template slot-scope="s"> {{ s.row.tsldNumber }} </template>
                </el-table-column>
                <el-table-column label="执行时间" width="120">
                  <template slot-scope="s">
                    <el-popover trigger="hover" placement="top">
                      <p>{{ s.row.tsldTime }}</p>
                      <div slot="reference" class="name-wrapper">
                        {{ s.row.tsldTime | handleText }}
                      </div>
                    </el-popover>
                  </template>
                </el-table-column>
                <el-table-column label="任务类型" width="120">
                  <template slot-scope="s"> {{ s.row.tsldType }} </template>
                </el-table-column>
              </el-table>
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[4, 8, 12, 16]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
              >
              </el-pagination>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      checkList: [],
      time: "",
      a1: false,
      a2: false,
      Thenursingleveldetails: [],
      pinci: "",
      leibei: "",
      jb: [],
      tableData: [
        {
          nuId: 0,
          nuNname: "",
          nuMonry: "",
          nuWay: "",
          tsldType: "",
          tsldNumber: "",
          tsldTime: "",
        },
      ],
      form: {},
      innerVisible: false,
      dialogTableVisibleadd: false,
      formadd: {},
      pagesize: 0,
      tslMoney: 0,
      currentPage: 0,
      total: 0,
      pagesize: 0,
      size: 4,
      feiyong: [],
      r: {},
      fullscreenLoading: false,
    };
  },
  filters: {
    handleText(value) {
      if (!value) return "";
      if (value.length > 5) {
        return value.slice(0, 5) + "...";
      }
      return value;
    },
  },
  methods: {
    loding() {
      this.$http.get("tnsl/all").then((res) => {
        this.jb = res;
      });

      this.$http.get("nurc/allall").then((res) => {
        this.tableData = res;
      });
    },
    //确认添加任务设置
    addsss() {
      if (this.a1 == true) {
        var i1 = 0;
        this.tableData.forEach((t) => {
          if (t.nuId == this.r.nuId) {
            console.log(t.nuName);
            console.log(i1);
            var str = "";
            for (var i = 0; i < this.checkList.length; i++) {
              str += this.checkList[i] + ",";
            }
            //去掉最后一个逗号(如果不需要去掉，就不用写)
            if (str.length > 0) {
              str = str.substr(0, str.length - 1);
            }
            this.tableData[i1].tsldTime = str;
            this.tableData[i1].tsldNumber = this.leibei;
            this.tableData[i1].tsldType = this.pinci;
          }
          i1++;
        });
      } else {
        var i = 0;
        this.tableData.forEach((t) => {
          if (t.nuId == this.r.nuId) {
            console.log(t.nuName);
            console.log(i);
            var str = "";
            str = this.time;
            this.tableData[i].tsldTime = str;
            this.tableData[i].tsldNumber = this.leibei;
            this.tableData[i].tsldType = this.pinci;
          }
          i++;
        });
      }
      this.leibei = "";
      this.pinci = "";
      this.time = "";
      this.checkList = [];
      this.a1 = false;
      this.a2 = false;
      this.innerVisible = false;
      this.dialogTableVisibleadd = true;
    },
    //选择设置
    xuanze(row) {
      this.r = {};
      this.r = row;
      this.innerVisible = true;
      this.dialogTableVisibleadd = false;
    },
    //级别选取
    jibei(val) {
      this.form = val;
      this.$http
        .get(
          "thvt/allbyid?num=1&size=" + this.size + "&tslid=" + this.form.tslId
        )
        .then((res) => {
          this.Thenursingleveldetails = res.list;
          this.pagesize = res.pageSize;
          this.currentPage = res.pageNum;
          this.total = res.total;
        });
    },
    //级别选取上下页跳转
    handleCurrentChange(val) {
      this.$http
        .get(
          "thvt/allbyid?num=" +
            val +
            "&size=" +
            this.size +
            "&tslid=" +
            this.form.tslId
        )
        .then((res) => {
          this.Thenursingleveldetails = res.list;
          this.pagesize = res.pageSize;
          this.currentPage = res.pageNum;
          this.total = res.total;
        });
    },
    //级别选取切换当前页条数
    handleSizeChange(val) {
      this.size = val;
      this.$http
        .get(
          "thvt/allbyid?num=1&size=" + this.size + "&tslid=" + this.form.tslId
        )
        .then((res) => {
          this.Thenursingleveldetails = res.list;
          this.pagesize = res.pageSize;
          this.currentPage = res.pageNum;
          this.total = res.total;
        });
    },
    //选中的护理
    handleSelectionChange(val) {
      this.feiyong = val;
      this.tslMoney = 0;
      this.feiyong.forEach((f) => {
        this.tslMoney = this.tslMoney + f.nuMonry;
      });
    },
    //修改级别
    xiugaijb() {
      if (this.form.tslName == undefined) {
        this.$message.error("请选择需要修改的护理级别！");
      }
    },
    //确认新增
    addqd() {
      if (this.feiyong.length == 0) {
        this.$message.error("请选择对应级别的护理！");
      } else {
        this.fullscreenLoading = true;
        this.$http
          .post("tnsl/add", {
            tslName: this.formadd.tslName,
            tslMoney: this.tslMoney,
            tslOther: this.formadd.tslOther,
            tslObject: this.formadd.tslObject,
          })
          .then((id) => {
            console.log(id);
            this.feiyong.forEach((r) => {
              console.log(r.nuId);
              this.$http.post("thvt/add/" + id + "/" + r.nuId, {
                tsldType: r.tsldType,
                tsldNumber: r.tsldNumber,
                tsldTime: r.tsldTime,
              });
            });
            setTimeout(() => {
              this.loding();
              this.formadd = {};
              this.tslMoney = 0;
              this.feiyong = [];
              this.$message({
                showClose: true,
                message: "护理级别设置成功！",
                type: "success",
              });
              this.fullscreenLoading = false;
            }, 2000);
          });
      }
    },
    //改变事件
    gaibian() {
      if (this.pinci == "周任务") {
        this.a1 = true;
        this.a2 = false;
      } else {
        this.a1 = false;
        this.a2 = true;
      }
    },
    //关闭新增弹窗提示
    handleClose(done) {
      this.$confirm("关闭将清空时间安排，是否继续?")
        .then((_) => {
          done();
          this.loding();
          this.formadd = {};
          this.tslMoney = 0;
        })
        .catch((_) => {});
    },
  },
  created() {
    this.loding();
  },
};
</script>

<style scoped>
.div {
  margin: 0;
  padding: 20px;
}
.li1 {
  color: #909399;
  font-size: 14px;
  margin-top: 10px;
}

.li1:hover {
  color: #409eff;
  cursor: pointer;
}
</style>