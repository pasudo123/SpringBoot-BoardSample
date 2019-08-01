<template>
    <div id="testView">
        <h2>테스트하기 위한 화면</h2>
        <hr>

        <v-flex>
            <div class="textFieldWrapper">
                <v-text-field
                        color="blue"
                        label="Message"
                        type="text"
                        v-model="textA0"
                        single-line
                ></v-text-field>

                <v-text-field
                        color="blue"
                        label="Message"
                        type="text"
                        v-model="textA1"
                        single-line
                ></v-text-field>
            </div>
            <br>
            <button @click="clickEvent1">initText</button>
            <br><br>
            <button @click="clickEvent2">initObject</button>
        </v-flex>

    </div>
</template>

<script>

    import {mapMutations} from 'vuex'
    import {createHelpers} from 'vuex-map-fields'

    const {mapFields: A0} = createHelpers({
        getterType: 'getA0Field',
        mutationType: 'updateA0Field'
    });

    const {mapFields: A1} = createHelpers({
        getterType: 'getA1Field',
        mutationType: 'updateA1Field'
    });

    export default {
        name: "TestView",
        data() {
            return {}
        },
        computed: {
            ...A0({
                'textA0': 'a0State.text'
            }),

            ...A1({
                'textA1': 'a1State.text'
            })
        },
        methods: {

            ...mapMutations(['initText', 'initObject']),

            clickEvent1() {
                this.initText();
            },

            clickEvent2() {
                this.initObject();
            }
        }
    }
</script>

<style scoped>
    div#testView {
        margin: 0 auto;
        text-align: center;
    }

    div.textFieldWrapper {
        margin: 20px auto 0 auto;
        width: 600px;
    }

    button {
        padding: 8px 15px 8px 15px;
        background-color: darkgreen;
        color: white;
        border-radius: 5px;
    }
</style>