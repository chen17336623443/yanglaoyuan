<template>
  <div>
    <div>
        <el-row>

        </el-row>
    </div>

  </div>
</template>

<script>
export default {
    data(){
        return{
            current:1,
            pageSize:5,
            total:0,
            foodData:[],
            foodtypeData:[]
        }
    },methods:{
        loadData(){
            let that=this;
            this.$axios.get("http://localhost:8089/food/all?pageNo="+this.current+"&pageSize="+this.pageSize)
            .then(r=>{
                console.log(r);
                that.foodData=r.list;
                that.total=r.total;
            });
            this.$axios.get("http://localhost:8089/food/alltype")
            .then(r=>{
                console.log(r);
                that.foodtypeData=r;
            })
            
        }
    },created(){
        this.loadData();
    }
}
</script>

<style scoped>

</style>