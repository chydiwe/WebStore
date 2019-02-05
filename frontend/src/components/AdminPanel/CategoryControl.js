import React from "react";

export class CategoryControl extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        const {listCategory, addCategory, delCategory, status} = this.props;
        return (
            <div className={`adminPanel ${status}`}>
                <div className="addCategory"><p>Добавление категории</p>
                    <input ref={(node) => this._category = node} type="text" placeholder='Название категории'/>
                    <button type="button"
                            onClick={() => addCategory(this._category.value)}>Добавить категорию
                    </button>
                </div>
                <div className="delCategory">
                    <p>Удаление категории</p>
                    <select className='categoryItem' ref={(node) => this._categorys = node}>
                        {listCategory.map((item, index) =>
                            <option key={index} value={item.id}>{item.name}</option>
                        )}
                    </select>
                    <button onClick={() => delCategory(this._categorys.value)} type='button'>Удалить</button>
                </div>
            </div>
        );
    }

}