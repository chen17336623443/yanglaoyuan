<template>
  <div class="div">
    <el-tabs type="border-card">
      <el-tab-pane label="护理信息">
        <el-row>
          <!-- 护理类别列表卡片 -->
          <el-col :span="6" style="margin-left: 15px">
            <el-card class="box-card" style="height: 590px">
              <div slot="header" class="clearfix">
                <span>护理项目类别</span>
                <el-button
                  style="float: right; padding: 3px 0"
                  type="text"
                  @click="add"
                >
                  新增类别
                </el-button>
              </div>
              <ul style="list-style: none">
                <li
                  v-for="l in lb"
                  :key="l.index"
                  class="li1"
                  @click="leibei(l)"
                >
                  <span>{{ l.ntName }}</span>
                </li>
              </ul>
            </el-card>
          </el-col>

          <!-- 基本信息显示卡片 -->
          <el-col :span="17" style="margin-left: 15px">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>基本信息</span>
                <el-button
                  style="float: right; padding: 3px 0"
                  type="text"
                  @click="update"
                >
                  修改
                </el-button>
              </div>
              <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="类别名称">
                  <el-input
                    v-model="form.ntName"
                    style="width: 250px"
                    :readonly="true"
                  ></el-input>
                </el-form-item>
                <el-form-item label="类别备注">
                  <el-input
                    type="textarea"
                    v-model="form.ntOther"
                    rows="3"
                    :readonly="true"
                    resize="none"
                  ></el-input>
                </el-form-item>
              </el-form>
            </el-card>

            <!-- 类别修改弹窗 -->
            <el-dialog
              title="类别修改"
              :visible.sync="dialogFormVisible"
              width="40%"
            >
              <el-form :model="form1">
                <el-form-item label="类别名称">
                  <el-input
                    v-model="form1.ntName"
                    style="width: 250px"
                  ></el-input>
                </el-form-item>
                <el-form-item label="类别备注">
                  <el-input
                    type="textarea"
                    v-model="form1.ntOther"
                    rows="3"
                    style="width: 420px"
                    resize="none"
                  ></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="(dialogFormVisible = false), (form1 = {})"
                  >取 消</el-button
                >
                <el-button type="primary" @click="upqd"> 确 定 </el-button>
              </div>
            </el-dialog>

            <!-- 类别新增弹窗 -->
            <el-dialog
              title="类别新增"
              :visible.sync="dialogFormVisible2"
              width="40%"
            >
              <el-form :model="form2">
                <el-form-item label="类别名称">
                  <el-input
                    v-model="form2.ntName"
                    style="width: 250px"
                  ></el-input>
                </el-form-item>
                <el-form-item label="类别备注">
                  <el-input
                    type="textarea"
                    v-model="form2.ntOther"
                    rows="3"
                    style="width: 420px"
                    resize="none"
                  ></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="(dialogFormVisible2 = false), (form2 = {})">
                  取 消
                </el-button>
                <el-button type="primary" @click="addqd"> 确 定 </el-button>
              </div>
            </el-dialog>

            <!-- 项目列表信息显示 -->
            <el-card class="box-card" style="margin-top: 5px">
              <div slot="header" class="clearfix">
                <span>项目列表</span>
                <el-button-group style="float: right; margin-top: -5px">
                  <el-button size="small" plain @click="xzdialog = true">
                    新增护理
                  </el-button>
                  <el-button size="small" plain @click="xiugaihuli">
                    修改护理
                  </el-button>
                </el-button-group>
              </div>
              <el-table
                ref="multipleTable"
                :data="tableData"
                tooltip-effect="dark"
                size="mini"
                style="width: 100%"
                border
                @selection-change="handleSelectionChange"
                ><el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column prop="nuId" label="编号" width="60">
                </el-table-column>
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
                <el-table-column label="所需时间" width="100">
                  <template slot-scope="s"> {{ s.row.nuTime }}分钟 </template>
                </el-table-column>
                <el-table-column label="护理备注" width="100">
                  <template slot-scope="s">
                    <el-popover trigger="hover" placement="top">
                      <p>{{ s.row.nuOther }}</p>
                      <div slot="reference" class="name-wrapper">
                        {{ s.row.nuOther | handleText }}
                      </div>
                    </el-popover>
                  </template>
                </el-table-column>
                <el-table-column label="操作">
                  <template slot-scope="s">
                    <el-button type="text" size="mini" @click="xiugai(s.row)">
                      修改
                    </el-button>
                    <el-button type="text" size="mini" @click="del(s.row.nuId)">
                      移除
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[4, 8, 12, 16]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
              >
              </el-pagination>
            </el-card>
          </el-col>
        </el-row>

        <!-- 新增护理弹窗 -->
        <el-dialog title="新增护理项目" :visible.sync="xzdialog" width="50%">
          <el-form
            ref="xzform"
            :model="xzform"
            label-width="80px"
            :inline="true"
          >
            <el-form-item label="护理名称">
              <el-input v-model="xzform.nuName"></el-input>
            </el-form-item>
            <el-form-item label="护理类型">
              <el-select
                v-model="xzform.ntId"
                placeholder="请选择活动区域"
                style="width: 201px"
              >
                <el-option
                  v-for="l in lb"
                  :key="l.index"
                  :label="l.ntName"
                  :value="l.ntId"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="项目费用">
              <el-input v-model="xzform.nuMonry"></el-input>
            </el-form-item>
            <el-form-item label="所需时间">
              <el-input v-model="xzform.nuTime"></el-input>
            </el-form-item>
            <el-form-item label="收费方式">
              <el-radio-group v-model="xzform.nuWay">
                <el-radio label="元/月"></el-radio>
              </el-radio-group>
            </el-form-item>
            <br />
            <el-form-item label="项目简介">
              <el-input
                type="textarea"
                v-model="xzform.nuOther"
                rows="3"
                style="width: 501px"
                resize="none"
              ></el-input>
            </el-form-item>
            <br />
            <el-form-item style="margin-left: 396px">
              <el-button type="primary" @click="addhuliqr">立即创建</el-button>
              <el-button @click="(xzdialog = false), (xzform = {})"
                >取消</el-button
              >
            </el-form-item>
          </el-form>
        </el-dialog>
        <!-- 修改护理弹窗 -->
        <el-dialog title="修改护理项目" :visible.sync="xgdialog" width="50%">
          <el-form
            ref="xgform"
            :model="xgform"
            label-width="80px"
            :inline="true"
          >
            <el-form-item label="护理名称">
              <el-input v-model="xgform.nuName"></el-input>
            </el-form-item>
            <el-form-item label="护理类型">
              <el-select
                v-model="xgform.ntId"
                placeholder="请选择活动区域"
                style="width: 201px"
                disabled
              >
                <el-option
                  v-for="l in lb"
                  :key="l.index"
                  :label="l.ntName"
                  :value="l.ntId"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="项目费用">
              <el-input v-model="xgform.nuMonry"></el-input>
            </el-form-item>
            <el-form-item label="所需时间">
              <el-input v-model="xgform.nuTime"></el-input>
            </el-form-item>
            <el-form-item label="收费方式">
              <el-radio-group v-model="xgform.nuWay">
                <el-radio label="元/月" disabled></el-radio>
              </el-radio-group>
            </el-form-item>
            <br />
            <el-form-item label="项目简介">
              <el-input
                type="textarea"
                v-model="xgform.nuOther"
                rows="3"
                style="width: 501px"
                resize="none"
              ></el-input>
            </el-form-item>
            <br />
            <el-form-item style="margin-left: 396px">
              <el-button type="primary" @click="xiugaihuliqr"
                >立即创建</el-button
              >
              <el-button @click="(xgdialog = false), (xgform = {})"
                >取消</el-button
              >
            </el-form-item>
          </el-form>
        </el-dialog>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageSize: 0,
      total: 0,
      currentPage: 0,
      size: 4,
      pagenum: 0,
      xzform: {},
      xgform: {},
      form: {},
      form1: {},
      form2: {},
      tableData: [],
      dialogFormVisible: false,
      xzdialog: false,
      xgdialog: false,
      dialogFormVisible2: false,
      lb: [],
      ntid: 0,
      xuanzhong: [],
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
    //开始加载护理类别
    loding() {
      this.$http.get("nurst/all").then((res) => {
        this.lb = res;
      });
      this.$http.get("nurc/all?num=1&size=" + this.size).then((res) => {
        this.tableData = res.list;
        this.pagenum = 1;
        this.pagesize = res.pageSize;
        this.currentPage = res.pageNum;
        this.total = res.total;
      });
    },
    //护理切换每页条数
    handleSizeChange(val) {
      this.size = val;
      if (this.pagenum == 1) {
        this.$http.get("nurc/all?num=1&size=" + this.size).then((res) => {
          this.tableData = res.list;
          this.pagenum = 1;
          this.pagesize = res.pageSize;
          this.currentPage = res.pageNum;
          this.total = res.total;
        });
      } else if (this.pagenum == 2) {
        this.$http
          .get("nurc/byid?num=1&size=" + this.size + "&id=" + this.ntid)
          .then((res) => {
            this.tableData = res.list;
            this.pagenum = 2;
            this.pagesize = res.pageSize;
            this.currentPage = res.pageNum;
            this.total = res.total;
          });
      }
    },
    //选中表格行数据
    handleSelectionChange(val) {
      this.xuanzhong = val;
    },
    //修改
    xiugai(row) {
      this.xgdialog = true;
      this.xgform = row;
    },
    //删除
    del(id) {
      this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http.get("nurc/del?id=" + id).then((res) => {
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
            message: "已取消删除",
          });
        });
    },
    //新增护理
    addhuliqr() {
      this.$http
        .post("nurc/add/" + this.xzform.ntId, {
          nuName: this.xzform.nuName,
          nuMonry: this.xzform.nuMonry,
          nuWay: this.xzform.nuWay,
          nuTime: this.xzform.nuTime,
          nuOther: this.xzform.nuOther,
        })
        .then((res) => {
          this.$message({
            message: res,
            type: "success",
          });
          this.xzdialog = false;
          this.loding();
        });
    },
    //修改护理确认
    xiugaihuliqr() {
      this.$http
        .post("nurc/upd", {
          nuId: this.xgform.nuId,
          nuName: this.xgform.nuName,
          nuMonry: this.xgform.nuMonry,
          nuTime: this.xgform.nuTime,
          nuOther: this.xgform.nuOther,
        })
        .then((res) => {
          this.$message({
            message: res,
            type: "success",
          });
          this.xgdialog = false;
          this.loding();
        });
    },
    //修改护理
    xiugaihuli() {
      if (this.xuanzhong.length == 1) {
        this.xuanzhong.forEach((x) => {
          this.xiugai(x);
        });
      } else {
        this.$message.error("请选择正确的信息！");
      }
    },
    //护理切换上下页
    handleCurrentChange(val) {
      if (this.pagenum == 1) {
        this.$http
          .get("nurc/all?num=" + val + "&size=" + this.size)
          .then((res) => {
            this.tableData = res.list;
            this.pagenum = 1;
            this.pagesize = res.pageSize;
            this.currentPage = res.pageNum;
            this.total = res.total;
          });
      } else if (this.pagenum == 2) {
        this.$http
          .get(
            "nurc/byid?num=" + val + "&size=" + this.size + "&id=" + this.ntid
          )
          .then((res) => {
            this.tableData = res.list;
            this.pagenum = 2;
            this.pagesize = res.pageSize;
            this.currentPage = res.pageNum;
            this.total = res.total;
          });
      }
    },
    //护理类别信息查看
    leibei(val) {
      this.form = val;
      this.form1 = val;
      this.ntid = val.ntId;
      this.$http
        .get("nurc/byid?num=1&size=" + this.size + "&id=" + val.ntId)
        .then((res) => {
          this.tableData = res.list;
          this.pagenum = 2;
          this.pagesize = res.pageSize;
          this.currentPage = res.pageNum;
          this.total = res.total;
        });
    },
    //护理类别修改
    update() {
      if (
        this.form.ntName == undefined ||
        (this.form.ntOther == undefined && this.form.ntName == undefined)
      ) {
        this.$message.error("请选择需要修改的护理！");
      } else {
        this.dialogFormVisible = true;
      }
    },
    //新增护理类别
    add() {
      this.form2 = {};
      this.dialogFormVisible2 = true;
    },
    //修改护理类别确定
    upqd() {
      this.$http
        .get(
          "nurst/update?name=" +
            this.form1.ntName +
            "&other=" +
            this.form1.ntOther +
            "&ntid=" +
            this.form1.ntId
        )
        .then((res) => {
          this.$message({
            message: res,
            type: "success",
          });
          this.loding();
          this.dialogFormVisible = false;
        });
    },
    //新增护理类别确定
    addqd() {
      if (
        this.form2.ntName == undefined ||
        (this.form2.ntOther == undefined && this.form2.ntName == undefined) ||
        this.form2.ntName == "" ||
        (this.form2.ntOther == "" && this.form2.ntName == "")
      ) {
        this.$message.error("请将信息填写完整!");
      } else {
        this.$http
          .post("nurst/add", {
            ntName: this.form2.ntName,
            ntOther: this.form2.ntOther,
          })
          .then((res) => {
            this.$message({
              message: res,
              type: "success",
            });
            this.loding();
            this.form = {};
            this.dialogFormVisible2 = false;
          });
      }
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