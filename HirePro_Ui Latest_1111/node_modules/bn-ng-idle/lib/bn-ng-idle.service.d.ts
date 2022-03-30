import { Observable, Subject } from 'rxjs';
import * as ɵngcc0 from '@angular/core';
export declare class BnNgIdleService {
    private idle$;
    private timer$;
    private timeOutMilliSeconds;
    private idleSubscription;
    expired$: Subject<boolean>;
    constructor();
    startWatching(timeOutSeconds: any): Observable<any>;
    private startTimer;
    resetTimer(): void;
    stopTimer(): void;
    static ɵfac: ɵngcc0.ɵɵFactoryDef<BnNgIdleService, never>;
    static ɵprov: ɵngcc0.ɵɵInjectableDef<BnNgIdleService>;
}

//# sourceMappingURL=bn-ng-idle.service.d.ts.map