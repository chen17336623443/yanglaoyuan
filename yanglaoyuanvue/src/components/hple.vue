<template>
  <div style="padding: 20px;width:1090px">
    <fieldset style="padding: 20px">
      <legend>养老管理系统步骤分析</legend>
      <el-timeline>
        <el-timeline-item timestamp="入住登记" placement="top" color="#0bbd87" size="normal">
          <el-card>
            <el-steps :active="4" align-center>
              <el-step
                title="咨询"
                description="可以通过电话或者是媒体网络咨询我们智慧养老官方平台，我们定会以优质服务给予答复"
              ></el-step>
              <el-step
                title="登记"
                description="填写客户的基本信息（这里指老人），家属/健康信息"
              ></el-step>
              <el-step
                title="选择套餐"
                description="选择护理套餐和餐饮套餐，单项可以后期自行选择"
              ></el-step>
              <el-step
                title="入住"
                description="完成以上信息的填写，缴纳金额，即可入住在这里智慧养老欢迎您的参观访问"
              ></el-step>
            </el-steps>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="床位管理" placement="top" color="#0bbd87">
            <el-card>
            <el-steps :active="3" align-center>
              <el-step
                title="楼房管理"
                description="对养老院进行楼层的管理，可以开发新的楼栋"
              ></el-step>
              <el-step
                title="类型添加"
                description="添加楼房之后是对于床位类型的进行添加"
              ></el-step>
              <el-step
                title="床位添加"
                description="最后的床位是根据楼层和类型来进行设置"
              ></el-step>
            </el-steps>
            </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="护理管理" placement="top" color="#0bbd87">
            <el-card>
          <el-steps :active="4" align-center>
              <el-step
                title="护工管理"
                description="可以对护工安排新的老人，一个护工对应多个老人"
              ></el-step>
              <el-step
                title="护理项目"
                description="添加日常项目护理，也可以对护理类型进行添加"
              ></el-step>
              <el-step
                title="护理级别"
                description="添加完日常护理之后，可以将日常护理进行一个统一的设置一个套餐"
              ></el-step>
              <el-step
                title="护理记录"
                description="对单点护理的老人进行每个护理的费用和时间的记录"
              ></el-step>
            </el-steps>
            </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="餐饮管理" placement="top" color="#0bbd87">
          <el-card>
            <el-steps :active="4" align-center>
              <el-step
                title="食物管理"
                description="包括了食物的新增、以及食物基础信息的更正"
              ></el-step>
              <el-step
                title="食谱管理"
                description="给客户添加日常食谱"
              ></el-step>
              <el-step
                title="点餐管理"
                description="选择点餐客户，客户先选择食物加入购物车，购物车结账"
              ></el-step>
              <el-step
                title="送餐管理"
                description="查询送餐信息、以及点餐详情的查看"
              ></el-step>
            </el-steps>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="老人管理" placement="top" color="#0bbd87">
          <el-card>
            <el-steps :active="5" align-center>
              <el-step
                title="老人管理"
                description="包括了老人外出登记，出现意外，家属探视，退住结算"
              ></el-step>
              <el-step
                title="外出登记"
                description="记录老人的行踪，判断老人是否回院"
              ></el-step>
              <el-step
                title="意外登记"
                description="老人出现意外可以更好的记载缘由"
              ></el-step>
              <el-step
                title="探视来访"
                description="记录院外人员探视老人，保证老人的安全"
              ></el-step>
              <el-step
                title="退住结算"
                description="计算老人的所有消费，从而生成消费单，从而去财务结款"
              ></el-step>
            </el-steps>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="费用管理" placement="top" color="#0bbd87">
          <el-card>
              <el-steps :active="4" align-center>
              <el-step
                title="预存管理"
                description="老人的账户余额进行充值查询等"
              ></el-step>
              <el-step
                title="缴费管理"
                description="老人每个月的消费进行缴费"
              ></el-step>
              <el-step
                title="欠费管理"
                description="当余额不足时老人可以先欠费然后在欠费管理中缴费"
              ></el-step>
              <el-step
                title="费用流水"
                description="每次金额操作的查询"
              ></el-step>
            </el-steps>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </fieldset>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>