import React from 'react'
import connect from "react-redux/es/connect/connect";
import {addItemOnServer} from "../action/addItemonserver";
import "./css/AdminPanel.css"

class AdminPanel extends React.Component {
    constructor(props) {
        super(props)
        this.sendItem = this.sendItem.bind(this)
    }

    sendItem(e) {
        e.preventDefault()
        const str = `name=${this._nameItem.value}&category=${this._cateItem.value}&manufacturer=${this._manfItem.value}&cost=${this._costItem.value}&quantity=${this._quanItem.value}`
        console.log(str);
        console.log(this.props.addItem(str))
    }

    render() {
        return <div className='adminAddItem'>
            <input ref={(node) => this._nameItem = node} type="text" placeholder='name'/>
            <input ref={(node) => this._cateItem = node} type="text" placeholder='category'/>
            <input ref={(node) => this._manfItem = node} type="text" placeholder='manufacturer'/>
            <input ref={(node) => this._costItem = node} type="text" placeholder='cost'/>
            <input ref={(node) => this._quanItem = node} type="text" placeholder='quantity'/>
            <input type="submit" onClick={this.sendItem}/>
        </div>
    }

}

const mapStateToProps = (store) => {
    return {
        itemAdd: store.itemAdd
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        addItem: (item) => dispatch(addItemOnServer(item)),
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(AdminPanel)