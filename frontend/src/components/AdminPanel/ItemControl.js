import React from "react";

export class ItemControl extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        const {listCategory, listManufacturer, listProducts, addItem, delItem, status, addImg, addInfo} = this.props;
        return <div className={`adminPanel ${status}`}>
            <div className='Admin AddItem'>
                <p>Добавление товара</p>
                <input ref={(node) => this._nameItem = node} type="text" placeholder='name'/>
                <select className='categoryItem' ref={(node) => this._category = node}>
                    {listCategory.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <select className='manufacturerItem' ref={(node) => this._manufacturer = node}>
                    {listManufacturer.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <input ref={(node) => this._costItem = node} type="text" placeholder='cost'/>
                <input ref={(node) => this._quanItem = node} type="text" placeholder='quantity'/>
                <input ref={(node) => this._shortInfo = node} type="text" placeholder='shortInfo'/>
                <input ref={(node) => this._imgUrl = node} type="text" placeholder='images'/>
                <button type="button"
                        onClick={() => addItem(`name=${this._nameItem.value}&categoryId=${this._category.value}&manufacturerId=${this._manufacturer.value}&cost=${this._costItem.value}&quantity=${this._quanItem.value}`)}>Добавить
                </button>
            </div>
            <div className="Admin DelItem">
                <p>Удаление товаров</p>
                <select className='listProducts' ref={(node) => this._products = node}>
                    {listProducts.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <button onClick={() => delItem(this._products.value)} type="button">Удалить</button>
            </div>
            <div className="Admin DelItem">
                <p>Добавить картинку товару</p>
                <select className='listProducts' ref={(node) => this._product = node}>
                    {listProducts.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <input placeholder='url' ref={(node) => this._urlImg = node} type="text"/>
                <button onClick={() => addImg(this._product.value, this._urlImg.value)} type="button">Добавить
                    картинку
                </button>
            </div>
            <div className="Admin DelItem">
                <p>Добавить информация о товаре</p>
                <select className='listProducts' ref={(node) => this._productForInfo = node}>
                    {listProducts.map((item, index) =>
                        <option key={index} value={item.id}>{item.name}</option>
                    )}
                </select>
                <input placeholder='info' ref={(node) => this._info = node} type="text"/>
                <button onClick={() => addInfo(this._productForInfo.value, this._info.value)} type="button">Добавить
                    описание
                </button>
            </div>
        </div>
    }
}
