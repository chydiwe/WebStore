import React, {Component} from 'react'
import {registration} from "../action/registerUser";
import connect from "react-redux/es/connect/connect";
import {withRouter} from "react-router-dom";

class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            pass: "",
            passConf: "",
            name: "",
            surname: "",
            patronymic: "",
            phoneNumber: "",

            valids: {
                emailValid: false,
                passValid: true,
                passConfValid: true,
                nameValid: true,
                surnameValid: true,
                patrValid: true,
                phoneNumbValid: true
            }
        };
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePassChange = this.handlePassChange.bind(this);
        this.handlePassConfChange = this.handlePassConfChange.bind(this);
        this.handleFIOChange = this.handleFIOChange.bind(this);
        this.handlePhoneNumbChange = this.handlePhoneNumbChange.bind(this);
        this.submitInf = this.submitInf.bind(this)
    }

    submitInf(history) {
        let valids = this.state.valids;
        let formValid = Object.keys(valids).every(key => valids[key] === true);
        console.log(formValid);
        if (formValid) {
            const url = `email=${this.state.email}&password=${this.state.pass}&name=${this.state.name}&surname=${this.state.surname}&patronymic=${this.state.patronymic}${(this.state.phoneNumber !== '') ? `&phonenumber=${this.state.phoneNumber}` : ''}`;
            this.props.regUser(url);
            setTimeout(function () {
                history.push('/')
            }, 2000)
        } else {
            alert("Please enter correct data or missed fields!");
        }
    }

    toggleClass(inputElem, checkResult) {
        inputElem.classList = checkResult ? "validFieldValue" : "badFieldValue"
    }

    checkEmail(email) {
        let emailValid = email.match(/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,5})$/i);
        return !!(emailValid);
    }

    handleEmailChange(event) {
        this.setState({email: event.target.value});
        let checkResult = this.checkEmail(event.target.value);
        this.toggleClass(event.target, checkResult);
        this.setState({
            valids: {
                ...this.state.valids,
                emailValid: checkResult
            }
        });
    }

    handlePassChange(event) {
        this.setState({pass: event.target.value.trim()});
        let checkResult = event.target.value.trim().length > 8;
        this.toggleClass(event.target, checkResult);
        this.setState({
            valids: {
                ...this.state.valids,
                passValid: checkResult
            }
        });
    }

    handlePassConfChange(event) {
        this.setState({passConf: event.target.value.trim()});
        let checkResult = (this.state.pass === event.target.value.trim());
        this.toggleClass(event.target, checkResult);
        this.setState({
            valids: {
                ...this.state.valids,
                passValid: checkResult
            }
        });
    }

    handleFIOChange(event) {
        switch (event.target.id) {
            case "userName":
                this.setState({name: event.target.value.trim()});
                break;
            case "userSurname":
                this.setState({surname: event.target.value.trim()});
                break;
            case "userPatronymic":
                this.setState({patronymic: event.target.value.trim()});
                break;
            default:
                break;
        }
        if (event.target.value.trim() !== "") {
            this.toggleClass(event.target, true)
        }
        else {
            this.toggleClass(event.target, false)
        }
    }

    handlePhoneNumbChange(event) {
        this.setState({phoneNumber: event.target.value});
        let checkResult = event.target.value.match(/^8\([0-9]{3}\)[0-9]{3}-[0-9]{2}-[0-9]{2}$/g);
        this.toggleClass(event.target, checkResult);
    }

    render() {
        const Submit = withRouter(({history}) => (
            <button type='button' onClick={() => {
                this.submitInf(history)
            }}
            >Зарегистрироваться</button>));

        return (
            <div>
                <form onSubmit={this.handleSubmit} className="registrationForm">
                    <h2>Регистрация</h2>
                    <label htmlFor="email">E-mail</label><br/>
                    <input type="text" id="email" className="" onChange={this.handleEmailChange}
                           value={this.state.email}/><br/>

                    <label htmlFor="pass">Пароль</label><br/>
                    <input type="password" id="pass" onChange={this.handlePassChange} value={this.state.pass}/><br/>

                    <label htmlFor="passConf">Повторите пароль</label><br/>
                    <input type="password" id="passConf" onChange={this.handlePassConfChange}
                           value={this.state.passConf}/><br/>

                    <label htmlFor="userName">Имя</label><br/>
                    <input type="text" id="userName" onChange={this.handleFIOChange} value={this.state.name}/><br/>

                    <label htmlFor="userSurname">Фамилия</label><br/>
                    <input type="text" id="userSurname" onChange={this.handleFIOChange}
                           value={this.state.surname}/><br/>

                    <label htmlFor="userPatronymic">Отчество</label><br/>
                    <input type="text" id="userPatronymic" onChange={this.handleFIOChange}
                           value={this.state.patronymic}/><br/>

                    <label htmlFor="phoneNumber">Телефон</label><br/>
                    <input type="text" id="phoneNumber" onChange={this.handlePhoneNumbChange}
                           value={this.state.phoneNumber} placeholder="8(888)888-88-88"/><br/>
                    <Submit/>
                </form>
            </div>
        );
    }
}

const mapStateToProps = store => {
    return {
        isRegist: store.isRegist
    }
};
const mapDispatchToProps = dispatch => {
    return {
        regUser: (user) => dispatch(registration(user)),
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Register)