<template>
    <div style="border: 1px solid #ccc;">
        <Toolbar
            style="border-bottom: 1px solid #ccc"
            :editor="editor"
            :defaultConfig="toolbarConfig"
            :mode="mode"
        />
        <Editor
            style="height: 300px; overflow-y: hidden;"
            v-model="html"
            :defaultConfig="editorConfig"
            :mode="mode"
            @onCreated="onCreated"
        />
    </div>
</template>

<script>
import Vue from 'vue'
import {Editor, Toolbar} from '@wangeditor/editor-for-vue'


export default Vue.extend({
    components: {Editor, Toolbar},
    watch: {
        html(newValue, oldValue) {
            if("<p><br></p>"==newValue){
                newValue = ""
            }
            this.$emit('getContent',newValue)
        },
        content(newValue, oldValue){
            this.html = newValue
        },
    },
    props:{
        content: { // 父组件传递的数据
            type: String,
            default: ''
        },
    },
    data() {
        return {
            editor: null,
            html: '',
            toolbarConfig: {},
            editorConfig: {
                placeholder: "请输入内容...",
                readOnly: false,
                MENU_CONF: {
                    //上传参考https://www.wangeditor.com/v5/menu-config.html#%E5%9F%BA%E6%9C%AC%E9%85%8D%E7%BD%AE
                    uploadImage: {
                        // server: this.$http.defaults.baseURL+`/uploadImg1`,
                        server: `http://127.0.0.1:8088/shoppingwebsite/util/uploadImg1`,
                        // 超时时间，默认为 10 秒
                        timeout: 30 * 1000, // 5s
                        fieldName: "file",
                        // 将 meta 拼接到 url 参数中，默认 false
                        metaWithUrl: false, // join params to url
                        // 自定义上传参数，例如传递验证的 token 等。参数会被添加到 formData 中，一起上传到服务端。
                        meta: {dataKey: 0, bizType: "common"},
                        // 自定义增加 http  header
                        // headers: {
                        //   Accept: "text/x-json",
                        //   otherKey: "xxx"
                        // },
                        // 跨域是否传递 cookie ，默认为 false
                        withCredentials: true,
                        // 自定义增加 http  header
                        // headers: {
                        //   Accept: "text/x-json",
                        //   otherKey: "xxx"
                        // },
                        maxFileSize: 10 * 1024 * 1024, // 10M
                        base64LimitSize: 5 * 1024, // insert base64 format, if file's size less than 5kb
                        // 选择文件时的类型限制，默认为 ['image/*'] 。如不想限制，则设置为 []
                        allowedFileTypes: ["image/*"],
                        // 最多可上传几个文件，默认为 100
                        maxNumberOfFiles: 30,
                        onBeforeUpload(file) {
                            console.log("onBeforeUpload", file);

                            return file; // will upload this file
                            // return false // prevent upload
                        },
                        onProgress(progress) {
                            console.log("onProgress", progress);
                        },
                        onSuccess(file, res) {
                            console.log("onSuccess", file, res);
                        },
                        onFailed(file, res) {
                            alert(res.message);
                            console.log("onFailed", file, res);
                        },
                        onError(file, err, res) {
                            alert(err.message);
                            console.error("onError", file, err, res);
                        }
                    },
                    uploadVideo: {
                        server: `http://127.0.0.1:8088/shoppingwebsite/util/uploadImg1`,
                        fieldName: "file",
                        // 单个文件的最大体积限制，默认为 10M
                        maxFileSize: 500 * 1024 * 1024, // 5M
                        // 最多可上传几个文件，默认为 5
                        maxNumberOfFiles: 3,
                        // 选择文件时的类型限制，默认为 ['video/*'] 。如不想限制，则设置为 []
                        allowedFileTypes: ["video/*"],
                        // 自定义上传参数，例如传递验证的 token 等。参数会被添加到 formData 中，一起上传到服务端。
                        meta: {dataKey: 0, bizType: "video"},
                        // 将 meta 拼接到 url 参数中，默认 false
                        metaWithUrl: false,
                        // 自定义增加 http  header
                        // headers: {
                        //   Accept: "text/x-json",
                        //   otherKey: "xxx"
                        // },
                        // 跨域是否传递 cookie ，默认为 false
                        withCredentials: true,
                        // 超时时间，默认为 30 秒
                        timeout: 30 * 1000, // 15 秒
                        // 上传进度的回调函数
                        onProgress(progress) {
                            console.log("progress", progress);
                        },
                        // 单个文件上传成功之后
                        onSuccess(file, res) {
                            console.log(`${file.name} 上传成功`, res);
                        },
                        // 单个文件上传失败
                        onFailed(file, res) {
                            console.log(`${file.name} 上传失败`, res);
                        },
                        // 上传错误，或者触发 timeout 超时
                        onError(file, err, res) {
                            console.log(`${file.name} 上传出错`, err, res);
                        }
                    }
                }
            },
            mode: 'default', // or 'simple'
        }
    },
    methods: {
        onCreated(editor) {
            this.editor = Object.seal(editor) // 一定要用 Object.seal() ，否则会报错
        },
    },
    created() {
        // 模拟 ajax 请求，异步渲染编辑器
        // setTimeout(() => {
        //     this.html = '<p>模拟 Ajax 异步设置内容 HTML</p>'
        // }, 1500)
    },
    mounted() {
        // 模拟 ajax 请求，异步渲染编辑器
        // setTimeout(() => {
        //     this.html = '<p>模拟 Ajax 异步设置内容 HTML</p>'
        // }, 1500)
    },
    beforeDestroy() {
        const editor = this.editor
        if (editor == null) return
        editor.destroy() // 组件销毁时，及时销毁编辑器
    }
})
</script>

<style scoped>
@import "../../node_modules/@wangeditor/editor/dist/css/style.css";

</style>
