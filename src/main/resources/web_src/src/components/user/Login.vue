<template>
  <div class="hello">
    <Header> </Header>

    <el-container>
          <el-card class="center-card">
            <el-form  status-icon  label-width="0px" class="demo-ruleForm">
              <h2>{{$t("login")}}</h2>
              <el-form-item label="" >
                <el-input type="text" auto-complete="off" :placeholder="$t('username_description')" v-model="username"></el-input>
              </el-form-item>

              <el-form-item label="" >
                <el-input type="password" auto-complete="off" v-model="password" :placeholder="$t('password')"></el-input>
              </el-form-item>

               <el-form-item label="" >
                <el-button type="primary" style="width:100%;" @click="onSubmit" >{{$t("login")}}</el-button>
              </el-form-item>

              <el-form-item label="" >
                  <router-link to="/user/register">{{$t("register_new_account")}}</router-link>
                  &nbsp;&nbsp;&nbsp;

              </el-form-item>
            </el-form>
          </el-card>
    </el-container>

    <Footer> </Footer>

  </div>
</template>

<script>


export default {
  name: 'Login',
  components : {

  },
  data () {
    return {
      username: '',
      password: '',
    }

  },
  methods: {
      onSubmit() {
          //this.$message.success(this.username);
          var that = this ;
          var url = DocConfig.server+'/api/user/login';
          var params = new URLSearchParams();
          params.append('username', this.username);
          params.append('password', this.password);

          that.axios.post(url, params)
            .then(function (response) {
              if (response.data.error_code === 0 ) {
                that.$message.success("登录成功");
                //全局存储token
                window.localStorage["token"] = response.data.data;
                let redirect = decodeURIComponent(that.$route.query.redirect || '/item/index');
                that.$router.replace({
                  path: redirect
                });
              }else{
                that.$alert(response.data.error_message);
              }

            });
      },
  },
  mounted() {
    /*给body添加类，设置背景色*/
    document.getElementsByTagName("body")[0].className="grey-bg";
  },
  beforeDestroy(){
    /*去掉添加的背景色*/
    document.body.removeAttribute("class","grey-bg");
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.center-card a {
  font-size: 12px;
}

.center-card{
  text-align: center;
}

</style>
