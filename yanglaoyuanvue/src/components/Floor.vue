<template>
  <div>
    <el-tree :data="floortree" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
  </div>
</template>

<script>
  export default {
    name: "Floor",
    data(){
      return{
        floortree:[],
        data: [{
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
        }],
        defaultProps: {
          children: 'floorsByFid',
          label: 'fname'
        }

      }
    },
    methods:{
      handleNodeClick(data) {
        console.log(data);
      },


      tree(){
        this.$http.post("http://localhost:8089/floor/tree" )
          .then((res) => {
            this.floortree = res.floors
            console.log(res);
            console.log(res.floors);
          })
      }
    },
    created() {
      this.tree();
    }
  }
</script>

<style scoped>

</style>
