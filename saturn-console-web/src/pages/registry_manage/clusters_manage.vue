<template>
    <div v-loading="loading" element-loading-text="请稍等···">
        <div class="margin-20">
            <FilterPageList :data="zkClusterList" :total="total" :order-by="orderBy" :filters="filters">
                <template slot-scope="scope">
                    <el-form :inline="true" class="table-filter">
                        <el-form-item label="">
                            <el-input placeholder="搜索" v-model="filters.zkClusterKey"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" icon="el-icon-search" @click="scope.search">查询</el-button>
                        </el-form-item>
                    </el-form>
                    <div class="page-table">
                        <div class="page-table-header">
                            <div class="page-table-header-title"><i class="fa fa-list"></i>ZK集群信息
                                <el-button type="text" @click="getAllClusters"><i class="fa fa-refresh"></i></el-button>
                            </div>
                        </div>
                        <el-table stripe border @sort-change="scope.onSortChange" :data="scope.pageData" style="width: 100%">
                            <el-table-column prop="zkClusterKey" label="ID" sortable></el-table-column>
                            <el-table-column prop="zkAlias" label="名称"></el-table-column>
                            <el-table-column prop="offline" label="状态">
                                <template slot-scope="scope"> 
                                    <el-tag :type="scope.row.offline ? '' : 'success'" close-transition>{{statusMap[scope.row.offline]}}</el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column prop="zkAddr" label="连接串" width="500px" :show-overflow-tooltip="true"></el-table-column>
                        </el-table>
                    </div>
                </template>
            </FilterPageList>
        </div>
    </div>
</template>
<script>
export default {
  data() {
    return {
      loading: false,
      zkClusterList: [],
      filters: {
        zkClusterKey: '',
      },
      orderBy: 'zkClusterKey',
      total: 0,
      statusMap: {
        true: '离线',
        false: '在线',
      },
    };
  },
  methods: {
    getAllClusters() {
      this.loading = true;
      this.$http.get('/console/zkClusters').then((data) => {
        this.zkClusterList = data;
        this.total = this.zkClusterList.length;
      })
      .catch(() => { this.$http.buildErrorHandler('获取所有集群请求失败！'); })
      .finally(() => {
        this.loading = false;
      });
    },
  },
  created() {
    this.getAllClusters();
  },
};
</script>
