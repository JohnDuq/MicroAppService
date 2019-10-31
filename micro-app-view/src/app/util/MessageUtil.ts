import { Injectable } from '@angular/core';
import { Response } from './../model/Response';
import { MessageService } from 'primeng/components/common/messageservice';
import { Message } from '../model/Message';

@Injectable()
export class MessageUtil {

    public showMessage(messageService: MessageService, msg: Message): void {
        messageService.add({ severity: msg.type.toLowerCase(), summary: msg.type, detail: msg.detail });
    }

    public showMessages(messageService: MessageService, listMessages: Message[]): void {
        for (let msg of listMessages) {
            messageService.add({ severity: msg.type.toLowerCase(), summary: msg.type, detail: msg.detail });
        }
    }

    public showMessagesResponse(messageService: MessageService, response: Response): void {
        for (let msg of response.listMessages) {
            messageService.add({ severity: msg.type.toLowerCase(), summary: msg.type, detail: msg.detail });
        }
    }

}