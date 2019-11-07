import { Response } from './model/Response';
import { MessageUtil } from './util/MessageUtil';
import { Component } from '@angular/core';
import { MessageService } from 'primeng/components/common/messageservice';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [MessageService]
})
export class AppComponent {
  messageUtil: MessageUtil = new MessageUtil();
  blockedDocument: boolean = false;

  constructor(
    private messageService: MessageService
  ) { }

  blockDocument(): void {
    this.blockedDocument = true;
  }

  unblockDocument(): void {
    this.blockedDocument = false;
  }

  addMessageComplete(svrt: string, smmr: string, dtl: string): void {
    this.messageService.clear();
    this.messageService.add({ severity: svrt, summary: smmr, detail: dtl });
  }

  addMessageSuccesSummary(dtl: string): void {
    this.messageService.clear();
    this.messageService.add({ severity: 'success', summary: 'Success', detail: dtl });
  }

  addMessageInfoSummary(dtl: string): void {
    this.messageService.clear();
    this.messageService.add({ severity: 'info', summary: 'Info', detail: dtl });
  }

  addMessageWarnSummary(dtl: string): void {
    this.messageService.clear();
    this.messageService.add({ severity: 'warn', summary: 'Warning', detail: dtl });
  }

  addMessageErrorSummary(dtl: string): void {
    this.messageService.clear();
    this.messageService.add({ severity: 'error', summary: 'Error', detail: dtl });
  }

  showMessageResponse(rsp: Response): void {
    this.messageService.clear();
    this.messageUtil.showMessagesResponse(this.messageService, rsp);
  }

}
