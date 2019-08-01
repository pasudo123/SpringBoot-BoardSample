import {createHelpers} from "vuex-map-fields";

const { getA0Field, updateA0Field } = createHelpers({
    getterType: "getA0Field",
    mutationType: "updateA0Field"
});

export default {
    state: {
        a0State: {
            text: ''
        },
    },

    mutations: {
        updateA0Field,
    },

    getters: {
        getA0Field
    }
}