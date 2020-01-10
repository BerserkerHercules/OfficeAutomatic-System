<template>
  <div>
    <el-form :model="search">
      <el-form-item>
        <el-col :span="4">
				  <el-input size="small" style="width:150px" v-model="search.fileName" clearable="true"></el-input>
        </el-col>
        <el-col :span="10">
          <el-button type="primary" size="small" icon="el-icon-search" @click="getTableData">查询</el-button>
        </el-col>
      </el-form-item>
    </el-form>
    <div class="dropbox p-3">
        <h2 v-if="files.length===0" class="text-center">请把需上传的文件拖动至此</h2>
        <div class="border m-2 d-inline-block p-4" style="width:15rem" :key="file" v-for="file in files">
            <h5 class="mt-0">{{ file.name }}</h5>
            <div class="progress">
                <div class="progress-bar progress-bar-striped"
                :style="{ width: file.uploadPercentage+'%' }"></div>
            </div>
        </div>
    </div>
    <el-table class="fileTable" style="width:100%" :data="tableData">
      <el-table-column prop="fileName" label="文件名" sortable></el-table-column>
      <el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small" @click="down(scope.row)">下载</el-button>
          <el-button type="danger" size="small" @click="del(scope.row)" v-if="ifAdmin">删除</el-button>
				</template>
			</el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getDown,downFile,delFile } from '../../api/api'
export default {
  data() {
    return {
      tableData: [],
      files: [],
      ifAdmin: true,
      search: {
        fileName: ''
      }
    }
  },
  methods: {
    getTableData() {
      var obj = {
        fileName: this.search.fileName
      }
      getDown(obj).then((res) => [
        this.tableData = res.data
      ])
    },
    //上传操作函数
    uploadFile: function (file) {
      var item = {
        name: file.name,
        uploadPercentage: 0
      };
      this.files.push(item);
      var fd = new FormData();
      fd.append('file', file);
      var xhr = new XMLHttpRequest();
      xhr.open('POST', 'https://littlepros.mynatapp.cc/file/upload', true);
      xhr.upload.addEventListener('progress', function (e) {
        item.uploadPercentage = Math.round((e.loaded * 100) / e.total);
      }, false);
      xhr.send(fd);
    },
    onDrag: function (e) {
      e.stopPropagation();
      e.preventDefault();
    },
    onDrop: function (e) {
      e.stopPropagation();
      e.preventDefault();
      var dt = e.dataTransfer;
      for (var i = 0; i !== dt.files.length; i++) {
        this.uploadFile(dt.files[i]);
      }
    },
    //下载操作函数
    down(row) {
      // var obj = {
      //   fileName: row.fileName
      // }
      window.location.href="https://littlepros.mynatapp.cc/file/download?"+'fileName='+row.fileName
      // downFile(obj).then((res) => {
      //   if (res) {
      //     console.log("download===",res);
      //     const content = res.data;
      //     const blob = new Blob([content]);
      //     const fileName = `${row.fileName}`;
      //     if ("download" in document.createElement("a")) {
      //       // 非IE下载
      //       const elink = document.createElement("a");
      //       elink.download = fileName;
      //       elink.style.display = "none";
      //       elink.href = URL.createObjectURL(blob);
      //       document.body.appendChild(elink);
      //       elink.click();
      //       URL.revokeObjectURL(elink.href); // 释放URL 对象
      //       document.body.removeChild(elink);
      //     } else {
      //       // IE10+下载
      //       navigator.msSaveBlob(blob, fileName);
      //     }
      // }
      // })
    },
    //删除操作函数
    del(row) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var obj = {
          fileName: row.fileName
        }
        delFile(obj).then((res) => {
          this.$message({
          type: 'success',
          message: '删除成功!'
        });
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });          
      });
      this.getTableData()
    }
  },
  mounted: function () {
    this.getTableData()
    var user = sessionStorage.getItem('user');
    user = JSON.parse(user)
    user.permission=='1'?this.ifAdmin = true:this.ifAdmin = false
    var dropbox = document.querySelector('.dropbox');
    dropbox.addEventListener('dragenter', this.onDrag, false);
    dropbox.addEventListener('dragover', this.onDrag, false);
    dropbox.addEventListener('drop', this.onDrop, false);
  }
}

</script>

<style>
  .dropbox {
    margin: 20px 0;
    border: .25rem dashed #007bff;
    min-height: 5rem;
  }
  .fileTable td{
    border-bottom: 1px solid transparent !important
  } 
</style>
