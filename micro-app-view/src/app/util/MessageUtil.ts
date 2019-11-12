import { Injectable } from '@angular/core';
import { Response } from './../model/Response';
import { MessageService } from 'primeng/components/common/messageservice';
import { Message } from '../model/Message';

@Injectable()
export class MessageUtil {

    public showMessageSuccess(messageService: MessageService, msg: string): void {
        messageService.add({ severity: 'success', summary: 'success', detail: msg });
    }

    public showMessageError(messageService: MessageService, msg: string): void {
        messageService.add({ severity: 'error', summary: 'error', detail: msg });
    }

    public showMessage(messageService: MessageService, msg: Message): void {
        messageService.add({ severity: msg.type.toLowerCase(), summary: msg.type.toLowerCase(), detail: msg.detail.toLowerCase() });
    }

    public showMessages(messageService: MessageService, listMessages: Message[]): void {
        for (let msg of listMessages) {
            messageService.add({ severity: msg.type.toLowerCase(), summary: msg.type.toLowerCase(), detail: msg.detail.toLowerCase() });
        }
    }

    public showMessagesResponse(messageService: MessageService, response: Response): void {
        for (let msg of response.listMessages) {
            messageService.add({ severity: msg.type.toLowerCase(), summary: msg.type.toLowerCase(), detail: msg.detail.toLowerCase() });
        }
    }

}