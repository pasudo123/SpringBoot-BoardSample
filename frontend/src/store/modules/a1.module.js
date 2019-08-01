
import {createHelpers} from "vuex-map-fields";

const { getA1Field, updateA1Field } = createHelpers({
    getterType: "getA1Field",
    mutationType: "updateA1Field"
});

export default {
    state: {
        a1State: {
            text: ''
        },
        a1Text: ''
    },

    mutations: {
        updateA1Field,

        initObject(state) {

            state.a1State = {
                ...state.a1State,
                text: ''
            }
        }
    },

    getters: {
        getA1Field
    }
}