<template>
  <div>
    <el-container style="height: 500px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-tree :data="floortree" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
      </el-aside>
      <!--新增楼栋-->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-row>
            <el-col :span="12">
              <el-form-item label="楼栋名称" prop="fname">
               <el-input v-model="ruleForm.fname" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="楼层数量" prop="lcnumber">
                <el-input v-model="ruleForm.lcnumber" type="number" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="楼层名称格式" prop="lcstyle">
                <el-select v-model="ruleForm.lcstyle"  style="width: 200px">
                  <el-option label="第N层" value="1"></el-option>
                  <el-option label="楼名N层" value="2"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="房间数量" prop="fjnumber">
                <el-input v-model="ruleForm.fjnumber" type="number" style="width: 200px"></el-input>/层
              </el-form-item>
            </el-col>
          </el-row>


          <el-row>
            <el-col :span="7">
              <el-form-item label="房间名称前缀" prop="mcqz">
                <el-input v-model="ruleForm.mcqz"  style="width: 100px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="房间名称格式" >
                <el-input  :disabled="true"  style="width: 200px" value="几零几(XYZ)">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="房间名称后缀" prop="mchz">
                <el-input v-model="ruleForm.mchz"  style="width: 100px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>


          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
            <el-button @click="resetForm('ruleForm')">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-container  v-show="stepthree">
        <el-header style="text-align: left; font-size: 12px">
          <el-button @click="addloudong"> <i class="el-icon-circle-plus-outline" style="margin-right: 5px"></i>新增楼栋</el-button>
          <el-button> <i class="el-icon-remove-outline" style="margin-right: 5px"></i>删除楼栋</el-button>
        </el-header>
        <el-main>
          <el-table :data="tableData" tooltip-effect="dark"  @selection-change="handleSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column prop="fname" label="楼栋名称">
            </el-table-column>
            <el-table-column prop="floorsByFid.length" label="楼层数量">
            </el-table-column>
            <el-table-column  label="房间数量">
              <template slot-scope="scope">
                <!--display: none隐藏无关数据 然后根据length给numberhome进行累加 最终获得所有的房间数量-->
                <span style="display: none">{{numberhome =0 }}</span>
                <div v-for="item in scope.row.floorsByFid" >
                 <span style="display: none" v-if="item.floorsByFid!=null">
                    {{numberhome = numberhome+item.floorsByFid.length}}
                  </span>
                </div>
                {{numberhome}}
              </template >
            </el-table-column>
            <el-table-column  label="操作">
              <template slot-scope="scope">
                <el-button size="mini"><i class="el-icon-edit" style="margin-right: 5px"></i>修改名称</el-button>
              </template >
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>

      <!--新增楼层-->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisiblelc"
        width="50%"
        :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-row>
            <el-col :span="12">
              <el-form-item label="楼层名称" prop="fname">
                <el-input v-model="ruleForm.fname" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="房间数量" prop="fjnumber">
                <el-input v-model="ruleForm.fjnumber" type="number" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="7">
              <el-form-item label="房间名称前缀" prop="mcqz">
                <el-input v-model="ruleForm.mcqz"  style="width: 100px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="房间名称格式" >
                <el-input  :disabled="true"  style="width: 200px" value="几零几(XYZ)">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="房间名称后缀" prop="mchz">
                <el-input v-model="ruleForm.mchz"  style="width: 100px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item>
            <el-button type="primary" @click="submitFormlc('ruleForm')">立即创建</el-button>
            <el-button @click="resetFormlc('ruleForm')">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-container  v-show="stepone">
        <el-header style="text-align: left; font-size: 12px">
          <el-button @click="addlouchen"><i class="el-icon-circle-plus-outline" style="margin-right: 5px" ></i>新增楼层</el-button>
          <el-button><i class="el-icon-remove-outline" style="margin-right: 5px"></i>删除楼层</el-button>
        </el-header>
        <el-main>
          <el-table :data="tableData"tooltip-effect="dark"  @selection-change="handleSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column prop="fname" label="楼层名称" >
            </el-table-column>
            <el-table-column prop="floorsByFid.length" label="房间数量">
            </el-table-column>
            <el-table-column  label="操作">
              <template slot-scope="scope">
                <el-button size="mini"><i class="el-icon-edit" style="margin-right: 5px"></i>修改名称</el-button>
              </template >
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>

      <!--新增房间-->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisiblefj"
        width="50%"
        :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-row>
            <el-col :span="12">
              <el-form-item label="楼层名称" prop="fname">
                <el-input v-model="ruleForm.fname" style="width: 200px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item>
            <el-button type="primary" @click="submitFormfj('ruleForm')">立即创建</el-button>
            <el-button @click="resetFormfj('ruleForm')">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-container  v-show="steptwo">
        <el-header style="text-align: left; font-size: 12px">
          <el-button @click="addfangjain"><i class="el-icon-circle-plus-outline" style="margin-right: 5px"></i>新增房间</el-button>
          <el-button> <i class="el-icon-remove-outline" style="margin-right: 5px"></i>删除房间</el-button>

        </el-header>
        <el-main>
          <el-table :data="tableData"tooltip-effect="dark"  @selection-change="handleSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column prop="fname" label="房间名称">
            </el-table-column>
            <el-table-column label="床位数量" >
            </el-table-column>
            <el-table-column  label="操作">
              <template slot-scope="scope">
                <el-button size="mini"><i class="el-icon-edit" style="margin-right: 5px"></i>修改名称</el-button>
              </template >
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>

      <el-container  v-show="steplost">
        <el-header style="text-align: left; font-size: 12px">
          <el-button><i class="el-icon-circle-plus-outline" style="margin-right: 5px"></i>新增床位</el-button>
          <el-button> <i class="el-icon-remove-outline" style="margin-right: 5px"></i>删除床位</el-button>

        </el-header>
        <el-main>
          <el-table :data="tableData">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="fname" label="楼层名称" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>


    </el-container>



  </div>
</template>

<script>
  export default {
    name: "Floor",
    data(){
      return{
        floortree:[],
        tableData:[],
        data: [
            {
          label: '一级 1',
          children: [{
            label: '二级 1-1',
            children: [{
              label: '三级 1-1-1'
            }]
          }]
            }, {
          label: '一级 2',
          children: [{
            label: '二级 2-1',
            children: [{
              label: '三级 2-1-1'
            }]
          }, {
            label: '二级 2-2',
            children: [{
              label: '三级 2-2-1'
            }]
          }]
            }, {
          label: '一级 3',
          children: [{
            label: '二级 3-1',
            children: [{
              label: '三级 3-1-1'
            }]
          }, {
            label: '二级 3-2',
            children: [{
              label: '三级 3-2-1'
            }]
          }]
        }
        ],
        defaultProps: {
          children: 'floorsByFid',
          label: 'fname'
        },
        /*初始详细信息*/
        stepthree:true,
        /*初始详细信息*/
        stepone:false,
        /*点击楼层显示详情数据*/
        steptwo:false,
        steplost:false,
        /*被选中的信息*/
        multipleSelection :[],
        /*添加楼栋的from 对话*/
        dialogVisible:false,
        rules: {
          fname: [
            { required: true, message: '请输入楼栋名称', trigger: 'blur' }
          ],
          lcnumber: [
            { required: true, message: '请输楼层数量', trigger: 'blur' }
          ],
          fjnumber: [
            { required: true, message: '请输房间数量', trigger: 'blur' }
          ],
        },
        ruleForm:{
          fname:'',
          lcnumber:'',
          fjnumber:'',
          lcstyle:'',
          mcqz:'',
          mchz:'',
        },
        /*新增楼层*/
        dialogVisiblelc:false,
        fid:0,
        /*新增房间*/
        dialogVisiblefj:false,
      }
    },
    methods:{
      /*打开 添加房间的from 对话*/
      addfangjain(){
        this.ruleForm={
          fname : '',
          lcnumber:'',
          fjnumber:'',
          lcstyle:'',
          mcqz:'',
          mchz:'',
        }
        this.dialogVisiblefj = true
      },
      /* 添加房间的from验证 新增*/
      submitFormfj(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.ruleForm)
            console.log(this.fid);
            this.$axios.post("http://localhost:8089/floor/insertFangjian",this.$qs.stringify({
              fname : this.ruleForm.fname,
              fjnumber : this.ruleForm.fjnumber,
              lcnumber : this.ruleForm.lcnumber,
              lcstyle : this.ruleForm.lcstyle,
              mchz : this.ruleForm.mchz,
              mcqz : this.ruleForm.mcqz,
              fid : this.fid
            }))
              .then(r=>{
                this.tree();
                this.dialogVisiblefj =false;
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      /*添加房间的from 关闭对话*/
      resetFormfj(formName) {
        this.$refs[formName].resetFields();
        this.dialogVisiblefj =false;
      },

      /*打开 添加楼层的from 对话*/
      addlouchen(){
        this.ruleForm={
          fname : '',
          lcnumber:'',
          fjnumber:'',
          lcstyle:'',
          mcqz:'',
          mchz:'',
        }
        this.dialogVisiblelc = true
      },
      /* 添加楼层的from验证 新增*/
      submitFormlc(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.ruleForm)
            console.log(this.fid);
            this.$axios.post("http://localhost:8089/floor/insertLouchen",this.$qs.stringify({
              fname : this.ruleForm.fname,
              fjnumber : this.ruleForm.fjnumber,
              lcnumber : this.ruleForm.lcnumber,
              lcstyle : this.ruleForm.lcstyle,
              mchz : this.ruleForm.mchz,
              mcqz : this.ruleForm.mcqz,
              fid : this.fid
            }))
              .then(r=>{
                this.tree();
                this.dialogVisiblelc =false;
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      /*添加楼栋的from 关闭对话*/
      resetFormlc(formName) {
        this.$refs[formName].resetFields();
        this.dialogVisiblelc =false;
      },

      /* 添加楼栋的from验证 新增*/
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.ruleForm)
            this.$axios.post("http://localhost:8089/floor/insertLoudong",this.$qs.stringify({
              fname : this.ruleForm.fname,
              fjnumber : this.ruleForm.fjnumber,
              lcnumber : this.ruleForm.lcnumber,
              lcstyle : this.ruleForm.lcstyle,
              mchz : this.ruleForm.mchz,
              mcqz : this.ruleForm.mcqz
            }))
              .then(r=>{
                this.tree();
                    this.dialogVisible =false;

              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      /*添加楼栋的from 关闭对话*/
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.dialogVisible =false;
      },
      /*打开 添加楼栋的from 对话*/
      addloudong(){
        this.ruleForm={
          fname : '',
          lcnumber:'',
          fjnumber:'',
          lcstyle:'',
          mcqz:'',
          mchz:'',
        }
        this.dialogVisible = true
      },
      /*询问 添加楼栋的from 对话 是否关闭*/
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      /*多选删除获取选择中的数据*/
      handleSelectionChange(val) {
        this.multipleSelection = val;
        console.log(val)
      },
      /*树形控件的点击事件*/
      handleNodeClick(data) {
        console.log(data);
        console.log(data.ftype);

        if(data.ftype == 0){
          this.stepone = false;
          this.steptwo = false;
          this.stepthree = true;
          this.steplost = false
          this.tableData=data.floorsByFid;
          console.log(data.floorsByFid)

        }else if (data.ftype == 1){
          this.stepone = true;
          this.steptwo = false;
          this.stepthree = false;
          this.steplost = false;
          this.tableData=data.floorsByFid;
          this.fid = data.fid;
        }else if(data.ftype == 2){
          this.stepone = false;
          this.steptwo = true;
          this.stepthree = false;
          this.steplost = false;
          this.tableData=data.floorsByFid;
          this.fid = data.fid;
        }else if (data.ftype == 3){
          this.stepone = false;
          this.steptwo = false;
          this.stepthree = false;
          this.steplost = true;

        }
      },

      /*查询floor的树形数据*/
      tree(){
        this.$http.post("http://localhost:8089/floor/tree" )
          .then((res) => {
            this.floortree = res.floors;
            this.tableData = res.floors[0].floorsByFid;
          })
      },

    },
    created() {
      this.numberhome=0;
      this.tree();
    }
  }
</script>

<style scoped>
  .el-aside {
    border: 1px solid #B3C0D1;
    background-color: white;
    color: #333;
    text-align: center;
    height: 680px;
  }

  .el-main {
    background-color: white;
    color: #333;
    text-align: center;
    height: 680px;
  }

  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

</style>
