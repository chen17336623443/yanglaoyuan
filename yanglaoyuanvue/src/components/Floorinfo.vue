<template>
  <div style="margin: 5px;">
    <el-tabs >
      <el-tab-pane  v-for="(item, index) in ListLou"
                    :key="item.fid+''"
                    :label="item.fname" >

        <el-row>
          <el-col :span="2"><div class="grid-content bg-purple">
            <el-tag type="info">公共服务用房</el-tag>
          </div>
          </el-col>
          <el-col :span="22">
            <div class="grid-content bg-purple-light">
              <div v-for="a in item.floorinfosByFid">
                <div v-for="b in a.publicshousesByFiid">
                  <el-tag style="float: left;margin-right: 5px"  closable  @close="closepublicshouses(b.phid)">{{b.chname}}</el-tag>
                </div>
              </div>
              <el-tag style="float: left;margin-right: 5px;cursor: pointer" @click="addpublicshousesByFiid(item.fid)">+添加公共服务用房</el-tag>

            </div>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="2"><div class="grid-content bg-purple">

            <el-tag type="info">文化娱乐用房</el-tag>

          </div></el-col>
          <el-col :span="22">
            <div class="grid-content bg-purple-light" >
            <div v-for="a in item.floorinfosByFid">
              <div v-for="b in a.culturalhousesByFiid">
                <el-tag style="float: left;margin-right: 5px"  closable  @close="closeculturalhouses(b.chid)">{{b.chname}}</el-tag>
              </div>
            </div>
            <el-tag @click="insertculturalhouses(item.fid)" style="float: left;margin-right: 5px;cursor: pointer" >+添加文化娱乐用房</el-tag>
          </div>


          </el-col>
        </el-row>

        <el-row>
          <el-col :span="2"><div class="grid-content bg-purple">

            <el-tag type="info">房间设备</el-tag>

          </div></el-col>
          <el-col :span="22"><div class="grid-content bg-purple-light" >
            <div v-for="a in item.floorinfosByFid">
              <div v-for="b in a.equipmenthousesByFiid">
                <el-tag style="float: left;margin-right: 5px"  closable  @close="closeequipmenthouse(b.ehid)">{{b.shname}}</el-tag>
              </div>
            </div>
            <el-tag @click="insertequipmenthouse(item.fid)" style="float: left;margin-right: 5px;cursor: pointer">+添加房间设备</el-tag>
          </div>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="2"><div class="grid-content bg-purple">

            <el-tag type="info">安全保障设备</el-tag>

          </div></el-col>
          <el-col :span="22"><div class="grid-content bg-purple-light" >
            <div v-for="a in item.floorinfosByFid">
              <div v-for="b in a.safetyhousesByFiid">
                <el-tag style="float: left;margin-right: 5px"  closable  @close="closesafetyhouse(b.shid)">{{b.shname}}</el-tag>
              </div>
            </div>
            <el-tag @click="insertsafetyhouse(item.fid)" style="float: left;margin-right: 5px;cursor: pointer">+添加安全保障设备</el-tag>
          </div>
          </el-col>
        </el-row>


      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
    export default {
      name: "Floorinfo",
      data() {
        return {
          ListLou : [],
        };
      },
      methods: {
        closepublicshouses(a){
          console.log(a)
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.post("http://localhost:8089/floorinfo/closepublicshouses",this.$qs.stringify({
              id : a,
            }))
              .then(r=>{
                console.log(r);
                this.$message({
                  type: 'success',
                  message: '删除成功 '
                });
                this.selectFloorByFtype();
              })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        closeculturalhouses(a){
          console.log(a)
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.post("http://localhost:8089/floorinfo/closeculturalhouses",this.$qs.stringify({
              id : a,
            }))
              .then(r=>{
                console.log(r);
                this.$message({
                  type: 'success',
                  message: '删除成功 '
                });
                this.selectFloorByFtype();
              })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        closeequipmenthouse(a){
          console.log(a)
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.post("http://localhost:8089/floorinfo/closeequipmenthouse",this.$qs.stringify({
              id : a,
            }))
              .then(r=>{
                console.log(r);
                this.$message({
                  type: 'success',
                  message: '删除成功 '
                });
                this.selectFloorByFtype();
              })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        closesafetyhouse(a){
          console.log(a)
          this.$confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.post("http://localhost:8089/floorinfo/closesafetyhouse",this.$qs.stringify({
              id : a,
            }))
              .then(r=>{
                console.log(r);
                  this.$message({
                    type: 'success',
                    message: '删除成功 '
                  });
                this.selectFloorByFtype();
              })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },

      /*新增 安全保障设备*/
        insertsafetyhouse(a){
          this.$prompt('请输入名字', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /\S/,
            inputErrorMessage: '请输入名称'
          }).then(({ value }) => {
            this.$axios.post("http://localhost:8089/floorinfo/insertsafetyhouse",this.$qs.stringify({
              fid:a,
              chname:value
            }))
              .then(r=>{
                this.selectFloorByFtype();
                this.$message({
                  type: 'success',
                  message: '添加成功 '
                });
              })

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });

        },
        /*新增 房间设备*/
        insertequipmenthouse(a){
          this.$prompt('请输入名字', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /\S/,
            inputErrorMessage: '请输入名称'
          }).then(({ value }) => {
            this.$axios.post("http://localhost:8089/floorinfo/insertequipmenthouse",this.$qs.stringify({
              fid:a,
              chname:value
            }))
              .then(r=>{
                this.selectFloorByFtype();
                this.$message({
                  type: 'success',
                  message: '添加成功 '
                });
              })

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });

        },

        /*新增 文化娱乐用房*/
        insertculturalhouses(a){
          this.$prompt('请输入名字', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /\S/,
            inputErrorMessage: '请输入名称'
          }).then(({ value }) => {
            this.$axios.post("http://localhost:8089/floorinfo/insertculturalhouses",this.$qs.stringify({
              fid:a,
              chname:value
            }))
              .then(r=>{
                this.selectFloorByFtype();
                this.$message({
                  type: 'success',
                  message: '添加成功 '
                });
              })

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });

        },
        /*新增公共服务用房 */
        addpublicshousesByFiid(a){

          this.$prompt('请输入名字', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputPattern: /\S/,
            inputErrorMessage: '请输入名称'
          }).then(({ value }) => {
            this.$axios.post("http://localhost:8089/floorinfo/insert",this.$qs.stringify({
              fid:a,
              chname:value
            }))
              .then(r=>{
                this.selectFloorByFtype();
                this.$message({
                  type: 'success',
                  message: '添加成功 '
                });
              })

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });

        },
        /*查询所有的楼栋*/
        selectFloorByFtype(){
          this.$http.post("http://localhost:8089/floor/selectByFtype" )
            .then((res) => {
              this.ListLou = res;
              console.log(res)
            })
        },
      },
      created() {
        this.selectFloorByFtype();
      }
    }
</script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
  &:last-child {
     margin-bottom: 0;
   }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background-color: #f9fafc;
  }
  .bg-purple {
    background-color: #f9fafc;
  }
  .bg-purple-light {
    background-color: #f9fafc;
  }
  .grid-content {
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .el-tag {

    margin-top: 2px;
  }
</style>
